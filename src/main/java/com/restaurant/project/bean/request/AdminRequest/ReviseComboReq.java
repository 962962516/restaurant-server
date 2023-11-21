package com.restaurant.project.bean.request.AdminRequest;

public class ReviseComboReq {
    private int comboId;
    private String productName;
    private int productNumber;
    private String description;
    private int price;
    private int isHot;

    public ReviseComboReq(int comboId, String productName, int productNumber, String description, int price, int isHot) {
        this.comboId = comboId;
        this.productName = productName;
        this.productNumber = productNumber;
        this.description = description;
        this.price = price;
        this.isHot = isHot;
    }

    public int getComboId() {
        return comboId;
    }

    public void setComboId(int comboId) {
        this.comboId = comboId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getIsHot() {
        return isHot;
    }

    public void setIsHot(int isHot) {
        this.isHot = isHot;
    }
}
