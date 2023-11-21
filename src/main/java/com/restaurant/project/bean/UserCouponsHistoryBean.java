package com.restaurant.project.bean;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("user_coupons_history")
public class UserCouponsHistoryBean {
    private int userCouponsHistoryId;
    private Long userId;
    private int productId;
    private int comboId;
    private int isSingle;
    private Long oderNumber;
    private String time;

    public UserCouponsHistoryBean(int userCouponsHistoryId, Long userId, int productId, int comboId, int isSingle, Long oderNumber, String time) {
        this.userCouponsHistoryId = userCouponsHistoryId;
        this.userId = userId;
        this.productId = productId;
        this.comboId = comboId;
        this.isSingle = isSingle;
        this.oderNumber = oderNumber;
        this.time = time;
    }

    public UserCouponsHistoryBean() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getUserCouponsHistoryId() {
        return userCouponsHistoryId;
    }

    public void setUserCouponsHistoryId(int userCouponsHistoryId) {
        this.userCouponsHistoryId = userCouponsHistoryId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getComboId() {
        return comboId;
    }

    public void setComboId(int comboId) {
        this.comboId = comboId;
    }

    public int getIsSingle() {
        return isSingle;
    }

    public void setIsSingle(int isSingle) {
        this.isSingle = isSingle;
    }

    public Long getOderNumber() {
        return oderNumber;
    }

    public void setOderNumber(Long oderNumber) {
        this.oderNumber = oderNumber;
    }
}
