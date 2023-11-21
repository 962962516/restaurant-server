package com.restaurant.project.bean.response.UserRsponse;

public class WeeklyCouponsRsp {
    private Long weeklyCouponsId;
    private String weeklyCouponsName;
    private String description;
    private int productId;
    private int comboId;
    private int isSingle;
    private double price;
    private int integral;
    private double discount;
    private String imageUrl;

    public WeeklyCouponsRsp(Long weeklyCouponsId, String weeklyCouponsName, String description, int productId, int comboId, int isSingle, double price, int integral, double discount, String imageUrl) {
        this.weeklyCouponsId = weeklyCouponsId;
        this.weeklyCouponsName = weeklyCouponsName;
        this.description = description;
        this.productId = productId;
        this.comboId = comboId;
        this.isSingle = isSingle;
        this.price = price;
        this.integral = integral;
        this.discount = discount;
        this.imageUrl = imageUrl;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public WeeklyCouponsRsp() {
    }

    public Long getWeeklyCouponsId() {
        return weeklyCouponsId;
    }

    public void setWeeklyCouponsId(Long weeklyCouponsId) {
        this.weeklyCouponsId = weeklyCouponsId;
    }

    public String getWeeklyCouponsName() {
        return weeklyCouponsName;
    }

    public void setWeeklyCouponsName(String weeklyCouponsName) {
        this.weeklyCouponsName = weeklyCouponsName;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
