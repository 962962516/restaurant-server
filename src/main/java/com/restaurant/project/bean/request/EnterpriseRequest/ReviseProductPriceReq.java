package com.restaurant.project.bean.request.EnterpriseRequest;

public class ReviseProductPriceReq {
    private int productId;
    private int price;

    public ReviseProductPriceReq(int productId, int price) {
        this.productId = productId;
        this.price = price;
    }

    public ReviseProductPriceReq() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
