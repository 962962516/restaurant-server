package com.restaurant.project.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.restaurant.project.bean.*;
import com.restaurant.project.bean.request.EnterpriseRequest.ReviseComboPriceReq;
import com.restaurant.project.bean.request.EnterpriseRequest.ReviseProductPriceReq;
import com.restaurant.project.bean.response.EnterpriseResponse.AllClassRsp;
import com.restaurant.project.bean.response.EnterpriseResponse.ClassSalesRsp;
import com.restaurant.project.mapper.*;
import com.restaurant.project.utils.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EnterpriseService {
    @Resource
    private ProductMapper productMapper;
    @Resource
    private ComboMapper comboMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserServiceRatingMapper userServiceRatingMapper;
    @Resource
    private MonthSalesMapper monthSalesMapper;
    @Resource
    private ProductSalesVolumeMapper productSalesVolumeMapper;
    @Resource
    private AdminAddProductHistoryMapper adminAddProductHistoryMapper;
    @Resource
    private AdminAddComboHistoryMapper adminAddComboHistoryMapper;
    @Resource
    private AdminDeleteProductHistoryMapper adminDeleteProductHistoryMapper;

    public void reviseProductPrice(ReviseProductPriceReq reviseProductPriceReq){
        productMapper.updatePriceByproductId(reviseProductPriceReq.getPrice(), reviseProductPriceReq.getProductId());
    }
    public void reviseComboPrice(ReviseComboPriceReq reviseComboPriceReq){
        comboMapper.updatePriceByComboId(reviseComboPriceReq.getPrice(), reviseComboPriceReq.getComboId());
    }

    public List<UserBean> getAllAdmin(){
        QueryWrapper<UserBean> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_info",1);
        List<UserBean> userBeans = userMapper.selectList(queryWrapper);
        return userBeans;
    }
    public void changeAdminUsername(String name, Long userId){
        userMapper.changeAdminUsername(name,userId);
    }
    public void deleteAdmin(Long userId){
        QueryWrapper<UserBean> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        userMapper.delete(queryWrapper);
    }
    public void changeAdminPassword(Long userId, String password) throws NoSuchAlgorithmException {
        String hashedPassword = PasswordEncoder.encrypt(password); //密码加密
        userMapper.updateAdminPassword(hashedPassword,userId);
    }
    public List<UserServiceRatingBean> getServiceRating(){
        QueryWrapper<UserServiceRatingBean> queryWrapper = new QueryWrapper<>();
        List<UserServiceRatingBean> userServiceRatingBeans = userServiceRatingMapper.selectList(queryWrapper);
        return userServiceRatingBeans;
    }
    public void saveMonthSales(int month, int price){
        // 使用 switch 语句来获取月份名称
        String monthName;
        switch (month) {
            case 1:
                monthName = "January";
                break;
            case 2:
                monthName = "February";
                break;
            case 3:
                monthName = "March";
                break;
            case 4:
                monthName = "April";
                break;
            case 5:
                monthName = "May";
                break;
            case 6:
                monthName = "June";
                break;
            case 7:
                monthName = "July";
                break;
            case 8:
                monthName = "August";
                break;
            case 9:
                monthName = "September";
                break;
            case 10:
                monthName = "October";
                break;
            case 11:
                monthName = "November";
                break;
            case 12:
                monthName = "December";
                break;
            default:
                monthName = "Invalid Month";
                break;
        }
        monthSalesMapper.reviseMonthSales(price,monthName);
    }
    public List<MonthSalesBean> getMonthSales(){
        QueryWrapper<MonthSalesBean> queryWrapper = new QueryWrapper<>();
        List<MonthSalesBean> monthSalesBeans = monthSalesMapper.selectList(queryWrapper);
        return monthSalesBeans;
    }

    public ClassSalesRsp getClassSales(){
        QueryWrapper<ProductSalesVolumeBean> queryWrapper = new QueryWrapper<>();
        List<ProductSalesVolumeBean> productSalesVolumeBeans = productSalesVolumeMapper.selectList(queryWrapper);
        int combo = 0;
        int comboPrice = 0;
        int chickenBugger = 0;
        int chickenBuggerPrice = 0;
        int beefBugger = 0;
        int beefBuggerPrice = 0;
        int anGeSi = 0;
        int anGeSiPrice = 0;
        int chicken = 0;
        int chickenPrice = 0;
        int snack = 0;
        int snackPrice = 0;
        int coffee = 0;
        int coffeePrice = 0;
        for (ProductSalesVolumeBean productSalesVolumeBean : productSalesVolumeBeans) {
            Integer asideListNo = productMapper.getAsideListNoByProductName(productSalesVolumeBean.getProductName());
            if (asideListNo != null) {
                int price = productMapper.getPriceByProductName(productSalesVolumeBean.getProductName());
                if (asideListNo == 2) {
                    chickenBugger += productSalesVolumeBean.getSalesVolume();
                    chickenBuggerPrice += chickenBugger * price;
                }else if(asideListNo == 3){
                    beefBugger += productSalesVolumeBean.getSalesVolume();
                    beefBuggerPrice += beefBugger * price;
                }else if(asideListNo == 4){
                    anGeSi += productSalesVolumeBean.getSalesVolume();
                    anGeSiPrice += anGeSi * price;
                }else if(asideListNo == 5){
                    chicken += productSalesVolumeBean.getSalesVolume();
                    chickenPrice += chicken * price;
                }else if(asideListNo == 7){
                    snack += productSalesVolumeBean.getSalesVolume();
                    snackPrice += snack * price;
                }else if(asideListNo == 8){
                    coffee += productSalesVolumeBean.getSalesVolume();
                    coffeePrice += coffee * price;
                }
            }else {
                int price = comboMapper.getPriceByComboName(productSalesVolumeBean.getProductName());
                combo += productSalesVolumeBean.getSalesVolume();
                comboPrice += combo * price;
            }
        }
        ClassSalesRsp classSalesRsp = new ClassSalesRsp();
        classSalesRsp.setChickenBugger(chickenBugger);
        classSalesRsp.setBeefBugger(beefBugger);
        classSalesRsp.setAnGeSi(anGeSi);
        classSalesRsp.setChicken(chicken);
        classSalesRsp.setSnack(snack);
        classSalesRsp.setCoffee(coffee);
        classSalesRsp.setCombo(combo);

        classSalesRsp.setChickenBuggerPrice(chickenBuggerPrice);
        classSalesRsp.setBeefBuggerPrice(beefBuggerPrice);
        classSalesRsp.setAnGeSiPrice(anGeSiPrice);
        classSalesRsp.setChickenPrice(chickenPrice);
        classSalesRsp.setSnackPrice(snackPrice);
        classSalesRsp.setCoffeePrice(coffeePrice);
        classSalesRsp.setComboPrice(comboPrice);
        return classSalesRsp;
    }

    public List<AllClassRsp> classForCharts(int clazz){
        List<AllClassRsp> chickenBugger = new ArrayList<>();
        List<AllClassRsp> beefBugger = new ArrayList<>();
        List<AllClassRsp> aGeSi = new ArrayList<>();
        List<AllClassRsp> chicken = new ArrayList<>();
        List<AllClassRsp> snack = new ArrayList<>();
        List<AllClassRsp> coffee = new ArrayList<>();
        List<AllClassRsp> combo = new ArrayList<>();
        QueryWrapper<ProductSalesVolumeBean> queryWrapper = new QueryWrapper<>();
        List<ProductSalesVolumeBean> productSalesVolumeBeans = productSalesVolumeMapper.selectList(queryWrapper);
        for (ProductSalesVolumeBean productSalesVolumeBean : productSalesVolumeBeans) {
            Integer asideListNo = productMapper.getAsideListNoByProductName(productSalesVolumeBean.getProductName());

            AllClassRsp allClassRsp = new AllClassRsp();
            allClassRsp.setProductName(productSalesVolumeBean.getProductName());
            allClassRsp.setVolume(productSalesVolumeBean.getSalesVolume());
            if (asideListNo != null) {
                int price = productMapper.getPriceByProductName(productSalesVolumeBean.getProductName());
                allClassRsp.setPrice(productSalesVolumeBean.getSalesVolume() * price);
                if (asideListNo == 2) {
                    chickenBugger.add(allClassRsp);
                }else if(asideListNo == 3){
                    beefBugger.add(allClassRsp);
                }else if(asideListNo == 4){
                    aGeSi.add(allClassRsp);
                } else if (asideListNo == 5) {
                    chicken.add(allClassRsp);
                } else if (asideListNo == 7) {
                    snack.add(allClassRsp);
                } else if (asideListNo == 8) {
                    coffee.add(allClassRsp);
                }
            }else {
                int price = comboMapper.getPriceByComboName(productSalesVolumeBean.getProductName());
                allClassRsp.setPrice(price);
                combo.add(allClassRsp);
            }
        }
        if (clazz == 2){
            return chickenBugger;
        } else if (clazz == 3) {
            return beefBugger;
        } else if (clazz == 4) {
            return aGeSi;
        } else if (clazz == 5) {
            return chicken;
        } else if (clazz == 7) {
            return snack;
        } else if (clazz == 8) {
            return coffee;
        }else {
            return combo;
        }
    }

    public List<AdminAddProductHistoryBean> getAddProductHistory(){
        QueryWrapper<AdminAddProductHistoryBean> queryWrapper = new QueryWrapper<>();
        List<AdminAddProductHistoryBean> adminAddProductHistoryBeans = adminAddProductHistoryMapper.selectList(queryWrapper);
        return adminAddProductHistoryBeans;
    }
    public List<AdminAddComboHistoryBean> getAddComboHistory(){
        QueryWrapper<AdminAddComboHistoryBean> queryWrapper = new QueryWrapper<>();
        List<AdminAddComboHistoryBean> adminAddComboHistoryBeans = adminAddComboHistoryMapper.selectList(queryWrapper);
        return adminAddComboHistoryBeans;
    }
    public List<AdminDeleteProductHistoryBean> getAdminDeleteHistory(){
        QueryWrapper<AdminDeleteProductHistoryBean> queryWrapper = new QueryWrapper<>();
        List<AdminDeleteProductHistoryBean> adminAddComboHistoryBeans = adminDeleteProductHistoryMapper.selectList(queryWrapper);
        return adminAddComboHistoryBeans;
    }
}
