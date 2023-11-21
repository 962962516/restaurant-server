package com.restaurant.project.controller;

import com.restaurant.project.bean.*;
import com.restaurant.project.bean.request.AdminRequest.ChangeWeeklyCouponsReq;
import com.restaurant.project.bean.request.AdminRequest.ReviseComboReq;
import com.restaurant.project.bean.request.AdminRequest.ReviseProductNumberReq;
import com.restaurant.project.bean.request.AdminRequest.ReviseProductReq;
import com.restaurant.project.bean.response.AdminResponse.AddProductHistoryRsp;
import com.restaurant.project.mapper.AdminAddProductHistoryMapper;
import com.restaurant.project.mapper.ComboMapper;
import com.restaurant.project.mapper.ProductImagesMapper;
import com.restaurant.project.mapper.ProductMapper;
import com.restaurant.project.service.AdminService;
import com.restaurant.project.utils.ErrorCode;
import com.restaurant.project.utils.JwtTokenUtil;
import com.restaurant.project.utils.RandomNumberGenerator;
import com.restaurant.project.utils.Response;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
@RestController
public class AdminController {
    @Resource
    private AdminService adminService;
    @Resource
    private ProductMapper productMapper;
    @Resource
    private ProductImagesMapper productImagesMapper;
    @Resource
    private AdminAddProductHistoryMapper adminAddProductHistoryMapper;
    @Resource
    private ComboMapper comboMapper;
    @ApiOperation("顾客订单")
    @GetMapping("/admin/userOder")
    public ResponseEntity<Object> userOder(){
        List<AdminOderBean> allUserOder = adminService.getAllUserOder();
        return ResponseEntity.ok(Response.success(allUserOder));
    }
    @ApiOperation("删除准备订单并保存到准备好订单中")
    @PostMapping("/admin/deleteAndSavaFinishedOder")
    public ResponseEntity<Object> deleteAndSavaFinishedOder(@RequestBody AdminOderBean adminOderBean){
        adminService.deleteOder(adminOderBean);
        adminService.saveFinishedOder(adminOderBean);
        return ResponseEntity.ok(Response.success());
    }
    @ApiOperation("查询已经完成的订单")
    @GetMapping("/admin/finishedUserOder")
    public ResponseEntity<Object> finishedUserOder(){
        List<AdminOderFinishedBean> finishedOder = adminService.getFinishedOder();
        return ResponseEntity.ok(Response.success(finishedOder));
    }

    @ApiOperation("查询所有单品信息")
    @GetMapping("/admin/allProduct")
    public ResponseEntity<Object> allProduct(){
        List<ProductBean> allProduct = adminService.getAllProduct();
        return ResponseEntity.ok(Response.success(allProduct));
    }

    @ApiOperation("修改单品信息")
    @PostMapping("/admin/reviseProduct")
    public ResponseEntity<Object> reviseProduct(@RequestBody ProductBean productBean){
        adminService.reviseProduct(productBean);
        return ResponseEntity.ok(Response.success());
    }
    @ApiOperation("查询所有套餐信息")
    @GetMapping("/admin/allCombo")
    public ResponseEntity<Object> allCombo(){
        List<ComboBean> allCombo = adminService.getAllCombo();
        return ResponseEntity.ok(Response.success(allCombo));
    }

    @ApiOperation("修改套餐信息")
    @PostMapping("/admin/reviseCombo")
    public ResponseEntity<Object> reviseCombo(@RequestBody ComboBean comboBean){
        adminService.reviseCombo(comboBean);
        return ResponseEntity.ok(Response.success());
    }
    @ApiOperation("修改餐品数量")
    @PostMapping("/admin/reviseProductNumber")
    public ResponseEntity<Object> reviseProductNumber(@RequestBody ReviseProductNumberReq reviseProductNumberReq,
                                                      @RequestHeader("Authorization") String token){
        //token解析出userId
        Claims claims = JwtTokenUtil.getClaimsFromToken(token);
        Long adminId = Long.parseLong((String) claims.get("userId"));

        adminService.reviseProductNumber(reviseProductNumberReq.getProductId(), reviseProductNumberReq.getNumber());
        adminService.saveAdminReviseNumberHistory(adminId, reviseProductNumberReq,1); //保存admin历史记录
        return ResponseEntity.ok(Response.success());
    }
    @ApiOperation("搜索单品")
    @GetMapping("/admin/searchProduct")
    public ResponseEntity<Object> searchProduct(@RequestParam("productName") String productName){
        List<ProductBean> list = adminService.searchProduct(productName);
        return ResponseEntity.ok(Response.success(list));
    }
    @ApiOperation("搜索套餐")
    @GetMapping("/admin/searchCombo")
    public ResponseEntity<Object> searchCombo(@RequestParam("productName") String productName){
        List<ComboBean> comboBeans = adminService.searchCombo(productName);
        return ResponseEntity.ok(Response.success(comboBeans));
    }

    @ApiOperation("餐品出库")
    @PostMapping("/admin/outputProductNumber")
    public ResponseEntity<Object> outputProductNumber(@RequestBody ReviseProductNumberReq reviseProductNumberReq,
                                                      @RequestHeader("Authorization") String token){
        //token解析出userId
        Claims claims = JwtTokenUtil.getClaimsFromToken(token);
        Long adminId = Long.parseLong((String) claims.get("userId"));

        adminService.reviseOutputProductNumber(reviseProductNumberReq.getProductId(), reviseProductNumberReq.getNumber());
        adminService.saveAdminReviseNumberHistory(adminId,reviseProductNumberReq,2);
        return ResponseEntity.ok(Response.success());

    }

    @ApiOperation("上传产品图片")
    @PostMapping("/admin/productPicture")
    public ResponseEntity<Object> productPicture(@RequestParam("file") MultipartFile file) {
        String uploadDir = "src/main/resources/static/images/";
        if (file.isEmpty()) {
            return ResponseEntity.ok(Response.error(ErrorCode.USER_TOKEN_ISTIMEOUT));
        }
        try{
            String imageUrl = adminService.saveFileToService(uploadDir,file);
            return ResponseEntity.ok(Response.success(imageUrl));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @ApiOperation("处理图片url请求，返回图片")
    @GetMapping("/images/{fileName:.+}")
    public ResponseEntity<byte[]> displayImage(@PathVariable String fileName) {
        try {
            org.springframework.core.io.Resource imageResource = adminService.loadImageAsResource(fileName);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // 可根据实际图片类型进行设置

            InputStream inputStream = imageResource.getInputStream();
            byte[] imageBytes = inputStream.readAllBytes();

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(imageBytes);
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation("删除产品图片")
    @DeleteMapping("/admin/deleteImage")
    public ResponseEntity<Object> deleteImage(@RequestParam String fileName){
        try {
            String imageDir = "src/main/resources/static/images/";
            File file = new File(imageDir + fileName);

            if (file.delete()) {
                return ResponseEntity.ok(Response.success());
            } else {
                return ResponseEntity.ok(Response.success(ErrorCode.NOT_DELETE_IMAGE));
            }
        } catch (Exception e) {
            return ResponseEntity.ok(Response.success(ErrorCode.DELETE_IMAGE));
        }
    }

    @ApiOperation("添加商品信息")
    @PostMapping("/admin/addProduct")
    public ResponseEntity<Object> addProduct(@RequestBody ProductBean productBean,
                                            @RequestHeader("Authorization") String token){
        //token解析出userId
        Claims claims = JwtTokenUtil.getClaimsFromToken(token);
        Long adminId = Long.parseLong((String) claims.get("userId"));

        productMapper.insert(productBean);
        //保存admin操作记录
        adminService.saveAdminAddProductHistory(adminId,productBean.getProductName());

        return ResponseEntity.ok(Response.success());
    }
    @ApiOperation("添加套餐信息")
    @PostMapping("/admin/addCombo")
    public ResponseEntity<Object> addCombo(@RequestBody ComboBean comboBean,
                                           @RequestHeader("Authorization") String token){
        //token解析出userId
        Claims claims = JwtTokenUtil.getClaimsFromToken(token);
        Long adminId = Long.parseLong((String) claims.get("userId"));

        comboMapper.insert(comboBean);
        adminService.saveAdminAddComboHistory(adminId,comboBean.getProductName());
        return ResponseEntity.ok(Response.success());
    }

    @ApiOperation("刷新时删除多余商品")
    @GetMapping("/admin/deleteMoreImages")
    public ResponseEntity<Object> deleteMoreImages(){
        adminService.deleteMoreImages();
        return ResponseEntity.ok(Response.success());
    }
    @ApiOperation("查询管理员入库记录")
    @GetMapping("/admin/addProductHistory")
    public ResponseEntity<Object> addProductHistory(@RequestParam("show") int show){
        List<AddProductHistoryRsp> addProductHistoryRspList = adminService.numberHistory(show);
        return ResponseEntity.ok(Response.success(addProductHistoryRspList));
    }
    @ApiOperation("删除单品餐品接口")
    @DeleteMapping ("/admin/deleteProduct/{productId}")
    public ResponseEntity<Object> deleteProduct(@PathVariable int productId,
                                                @RequestHeader("Authorization") String token){
        //token解析出userId
        Claims claims = JwtTokenUtil.getClaimsFromToken(token);
        Long adminId = Long.parseLong((String) claims.get("userId"));

        adminService.saveDeleteProduct(adminId,productId);
        adminService.deleteProduct(productId);
        return ResponseEntity.ok(Response.success());
    }
    @ApiOperation("删除套餐餐品接口")
    @DeleteMapping("/admin/deleteCombo/{comboId}")
    public ResponseEntity<Object> deleteCombo(@PathVariable int comboId,
                                              @RequestHeader("Authorization") String token){
        //token解析出userId
        Claims claims = JwtTokenUtil.getClaimsFromToken(token);
        Long adminId = Long.parseLong((String) claims.get("userId"));

        adminService.saveDeleteCombo(adminId, comboId);
        adminService.deleteCombo(comboId);
        return ResponseEntity.ok(Response.success());
    }

    @ApiOperation("每周更改优惠卷")
    @PostMapping("/admin/changeWeeklyCoupons")
    public ResponseEntity<Object> changeWeeklyCoupons(@RequestBody ChangeWeeklyCouponsReq changeWeeklyCouponsReq){
        WeeklyCouponsBean weeklyCouponsBean = adminService.setChangeWeeklyCouponInfo(changeWeeklyCouponsReq.getProductName(),changeWeeklyCouponsReq.getPrice());
        weeklyCouponsBean.setWeeklyCouponsId(changeWeeklyCouponsReq.getWeeklyCouponsId());
        weeklyCouponsBean.setIntegral(changeWeeklyCouponsReq.getIntegral());
        adminService.saveChangeWeeklyCoupon(weeklyCouponsBean);
        return ResponseEntity.ok(Response.success());
    }
}
