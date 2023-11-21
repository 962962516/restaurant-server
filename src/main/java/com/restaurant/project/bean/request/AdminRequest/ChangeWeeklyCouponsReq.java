package com.restaurant.project.bean.request.AdminRequest;

public class ChangeWeeklyCouponsReq {
    private Long weeklyCouponsId;
    private String productName;
    private int integral;
    private int price;

    public ChangeWeeklyCouponsReq() {
    }

    public ChangeWeeklyCouponsReq(Long weeklyCouponsId, String productName, int integral, int price) {
        this.weeklyCouponsId = weeklyCouponsId;
        this.productName = productName;
        this.integral = integral;
        this.price = price;
    }

    public Long getWeeklyCouponsId() {
        return weeklyCouponsId;
    }

    public void setWeeklyCouponsId(Long weeklyCouponsId) {
        this.weeklyCouponsId = weeklyCouponsId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}