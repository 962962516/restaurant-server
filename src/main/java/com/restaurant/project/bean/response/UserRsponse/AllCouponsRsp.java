package com.restaurant.project.bean.response.UserRsponse;

public class AllCouponsRsp {
    private String name;
    private int comboId;
    private int productId;
    private double priceAfterDiscount;
    private String description;
    private int isSingle;
    private String imageUrl;

    public AllCouponsRsp(String name, int comboId, int productId, double priceAfterDiscount, String description, int isSingle, String imageUrl) {
        this.name = name;
        this.comboId = comboId;
        this.productId = productId;
        this.priceAfterDiscount = priceAfterDiscount;
        this.description = description;
        this.isSingle = isSingle;
        this.imageUrl = imageUrl;
    }

    public AllCouponsRsp() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getComboId() {
        return comboId;
    }

    public void setComboId(int comboId) {
        this.comboId = comboId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getPriceAfterDiscount() {
        return priceAfterDiscount;
    }

    public void setPriceAfterDiscount(double priceAfterDiscount) {
        this.priceAfterDiscount = priceAfterDiscount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIsSingle() {
        return isSingle;
    }

    public void setIsSingle(int isSingle) {
        this.isSingle = isSingle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
