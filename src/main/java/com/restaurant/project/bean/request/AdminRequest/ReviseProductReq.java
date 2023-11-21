package com.restaurant.project.bean.request.AdminRequest;

public class ReviseProductReq {
    private String productId;
    private String productName;
    private String description;
    private int price;
    private int productCount;
    private int adiseListNo;
    private int isHot;

    public ReviseProductReq(String productId, String productName, String description, int price, int productCount, int adiseListNo, int isHot) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.productCount = productCount;
        this.adiseListNo = adiseListNo;
        this.isHot = isHot;
    }

    public int getAdiseListNo() {
        return adiseListNo;
    }

    public void setAdiseListNo(int adiseListNo) {
        this.adiseListNo = adiseListNo;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public ReviseProductReq() {
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

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public int getIsHot() {
        return isHot;
    }

    public void setIsHot(int isHot) {
        this.isHot = isHot;
    }
}
