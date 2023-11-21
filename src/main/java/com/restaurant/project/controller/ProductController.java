package com.restaurant.project.controller;

import com.restaurant.project.bean.response.UserRsponse.ProductAndComboRsp;
import com.restaurant.project.bean.response.UserRsponse.WeeklyCouponsRsp;
import com.restaurant.project.service.AdminService;
import com.restaurant.project.service.UserService;
import com.restaurant.project.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class ProductController {
    @Resource
    private UserService userService;
    @Resource
    private AdminService adminService;
    @ApiOperation("查询菜单对应单品食品")
    @RequestMapping("/UserAside")
    public ResponseEntity<Response> userOderFood(@RequestParam int adiseListNo){
        List<ProductAndComboRsp> productAndComboRspList = userService.findAllUserOderProductByAdiseListNo(adiseListNo);
        return ResponseEntity.ok(Response.success(productAndComboRspList));
    }
    @ApiOperation("查询菜单对应套餐食品")
    @RequestMapping("/serAsideCombo")
    public ResponseEntity<Response> userOderComboFood(){
        List<ProductAndComboRsp> productAndComboRspList = userService.findAllUserOderCombo();
        return ResponseEntity.ok(Response.success(productAndComboRspList));
    }
    @ApiOperation("每周优惠卷")
    @GetMapping("/user/weeklyCoupons")
    public ResponseEntity<Object> weeklyCoupons(){
        List<WeeklyCouponsRsp> list = new ArrayList<>();
        for(Long weeklyCouponsId = 100001L; weeklyCouponsId <= 100006; weeklyCouponsId++){
            WeeklyCouponsRsp weeklyCouponsRsp = userService.weeklyCoupon(weeklyCouponsId);
            list.add(weeklyCouponsRsp);
        }
        return ResponseEntity.ok(Response.success(list));
    }
    @ApiOperation("获取全部单品名")
    @GetMapping("/product/productName")
    public ResponseEntity<Object> productName(){
        List<String> allProductName = adminService.getAllProductName();
        return ResponseEntity.ok(Response.success(allProductName));
    }
    @ApiOperation("获取全部套餐名")
    @GetMapping("/product/comboName")
    public ResponseEntity<Object> comboName(){
        List<String> allComboName = adminService.getAllComboName();
        return ResponseEntity.ok(Response.success(allComboName));
    }
}
