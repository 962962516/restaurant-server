package com.restaurant.project.bean.response.UserRsponse;

public class ConsumeComboIntegralRsp {
    private int comboId;
    private int integral;
    private int price;
    private double discount;

    public ConsumeComboIntegralRsp(int comboId, int integral, int price, double discount) {
        this.comboId = comboId;
        this.integral = integral;
        this.price = price;
        this.discount = discount;
    }

    public ConsumeComboIntegralRsp() {
    }

    public int getComboId() {
        return comboId;
    }

    public void setComboId(int comboId) {
        this.comboId = comboId;
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

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
