package com.restaurant.project.bean.request.UserRequest;

public class ConsumeIntegralReq {
    private int productId;
    private int integral;

    public ConsumeIntegralReq(int productId, int integral) {
        this.productId = productId;
        this.integral = integral;
    }

    public ConsumeIntegralReq() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }
}
