package com.restaurant.project.bean.response.AdminResponse;

public class AllComboRsp {
    private int comboId;
    private String productName;
    private String description;
    private int price;
    private int isHot;

    public AllComboRsp(int comboId, String productName, String description, int price, int isHot) {
        this.comboId = comboId;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.isHot = isHot;
    }

    public AllComboRsp() {
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
