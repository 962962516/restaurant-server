package com.restaurant.project.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("weekly_coupons")
public class WeeklyCouponsBean {
    @TableId
    private Long WeeklyCouponsId;
    private double discount;
    private int productId;
    private int comboId;
    private int isSingle;
    private int integral;
    private String imageUrl;

    public WeeklyCouponsBean(Long weeklyCouponsId, double discount, int productId, int comboId, int isSingle, int integral, String imageUrl) {
        WeeklyCouponsId = weeklyCouponsId;
        this.discount = discount;
        this.productId = productId;
        this.comboId = comboId;
        this.isSingle = isSingle;
        this.integral = integral;
        this.imageUrl = imageUrl;
    }

    public WeeklyCouponsBean() {
    }

    public Long getWeeklyCouponsId() {
        return WeeklyCouponsId;
    }

    public void setWeeklyCouponsId(Long weeklyCouponsId) {
        WeeklyCouponsId = weeklyCouponsId;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
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

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
