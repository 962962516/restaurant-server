package com.restaurant.project.controller;

import com.restaurant.project.bean.*;
import com.restaurant.project.bean.request.EnterpriseRequest.ChangeAdminPassword;
import com.restaurant.project.bean.request.EnterpriseRequest.ChangeAdminUsernameReq;
import com.restaurant.project.bean.request.EnterpriseRequest.ReviseComboPriceReq;
import com.restaurant.project.bean.request.EnterpriseRequest.ReviseProductPriceReq;
import com.restaurant.project.bean.response.EnterpriseResponse.AllClassRsp;
import com.restaurant.project.bean.response.EnterpriseResponse.ClassSalesRsp;
import com.restaurant.project.service.EnterpriseService;
import com.restaurant.project.utils.ErrorCode;
import com.restaurant.project.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Slf4j
@RestController
public class EnterpriseController {
    @Resource
    private EnterpriseService enterpriseService;
    @ApiOperation("单品价格调整")
    @PostMapping("/enterprise/reviseProductPrice")
    public ResponseEntity<Object> reviseProductPrice(@RequestBody ReviseProductPriceReq reviseProductPriceReq){
        enterpriseService.reviseProductPrice(reviseProductPriceReq);
        return ResponseEntity.ok(Response.success());
    }
    @ApiOperation("单品价格调整")
    @PostMapping("/enterprise/reviseComboPrice")
    public ResponseEntity<Object> reviseComboPrice(@RequestBody ReviseComboPriceReq reviseComboPriceReq){
        enterpriseService.reviseComboPrice(reviseComboPriceReq);
        return ResponseEntity.ok(Response.success());
    }

    @ApiOperation("查询所有管理员用户")
    @GetMapping("/enterprise/allAdmin")
    public ResponseEntity<Object> allAdmin(){
        List<UserBean> allAdmin = enterpriseService.getAllAdmin();
        if (allAdmin != null) {
            return ResponseEntity.ok(Response.success(allAdmin));
        }else {
            return ResponseEntity.ok(Response.error(ErrorCode.NOT_HAVE_ADMIN_USER));
        }
    }

    @ApiOperation("更改管理员用户名")
    @PostMapping("/enterprise/changeAdminUsername")
    public ResponseEntity<Object> changeAdminUsername(@RequestBody ChangeAdminUsernameReq changeAdminUsernameReq){
        enterpriseService.changeAdminUsername(changeAdminUsernameReq.getUsername(),changeAdminUsernameReq.getUserId());
        return ResponseEntity.ok(Response.success());
    }
    @ApiOperation("删除管理员")
    @PostMapping("/enterprise/deleteAdmin")
    public ResponseEntity<Object> deleteAdmin(@RequestParam("userId") Long userId){
        enterpriseService.deleteAdmin(userId);
        return ResponseEntity.ok(Response.success());
    }
    @ApiOperation("修改管理员密码")
    @PostMapping("/enterprise/changeAdminPassword")
    public ResponseEntity<Object> changeAdminPassword(@RequestBody ChangeAdminPassword changeAdminPassword){
        try {
            enterpriseService.changeAdminPassword(changeAdminPassword.getUserId(),changeAdminPassword.getPassword());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(Response.success());
    }
    @ApiOperation("用户建议和投诉")
    @GetMapping("/enterprise/rating")
    public ResponseEntity<Object> rating(){
        List<UserServiceRatingBean> serviceRating = enterpriseService.getServiceRating();
        return ResponseEntity.ok(Response.success(serviceRating));
    }
    @ApiOperation("月份总销量统计")
    @GetMapping("/enterprise/monthSales")
    public ResponseEntity<Object> monthSales(){
        List<MonthSalesBean> monthSales = enterpriseService.getMonthSales();
        return ResponseEntity.ok(Response.success(monthSales));
    }

    @ApiOperation("类型总销量")
    @GetMapping("/enterprise/classSales")
    public ResponseEntity<Object> classSales(){
        ClassSalesRsp classSales = enterpriseService.getClassSales();
        return ResponseEntity.ok(Response.success(classSales));
    }
    @ApiOperation("鸡肉汉堡类所占销量价格")
    @GetMapping("/enterprise/classChickenBugger")
    public ResponseEntity<Object> classChickenBugger(@Param("clazz") int clazz){
        List<AllClassRsp> list = enterpriseService.classForCharts(clazz);
        return ResponseEntity.ok(Response.success(list));
    }

    @ApiOperation("新增单品操作记录")
    @GetMapping("/enterprise/addProductHistory")
    public ResponseEntity<Object> addProductHistory(){
        List<AdminAddProductHistoryBean> addProductHistory = enterpriseService.getAddProductHistory();
        return ResponseEntity.ok(Response.success(addProductHistory));
    }
    @ApiOperation("新增套餐操作记录")
    @GetMapping("/enterprise/addComboHistory")
    public ResponseEntity<Object> addComboHistory(){
        List<AdminAddComboHistoryBean> addComboHistory = enterpriseService.getAddComboHistory();
        return ResponseEntity.ok(Response.success(addComboHistory));
    }
    @ApiOperation("管理删除餐品记录")
    @GetMapping("/enterprise/adminDeleteHistory")
    public ResponseEntity<Object> adminDeleteHistory(){
        List<AdminDeleteProductHistoryBean> adminDeleteHistory = enterpriseService.getAdminDeleteHistory();
        return ResponseEntity.ok(Response.success(adminDeleteHistory));
    }

}
