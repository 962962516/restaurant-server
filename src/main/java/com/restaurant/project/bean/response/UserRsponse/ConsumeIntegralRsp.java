package com.restaurant.project.bean.response.UserRsponse;

public class ConsumeIntegralRsp {
    private int price;
    private double discount;
    private int couponId;

    public ConsumeIntegralRsp(int price, double discount, int couponId) {
        this.price = price;
        this.discount = discount;
        this.couponId = couponId;
    }

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public ConsumeIntegralRsp() {
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
