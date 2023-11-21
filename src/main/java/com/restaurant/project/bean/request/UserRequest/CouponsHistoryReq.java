package com.restaurant.project.bean.request.UserRequest;

public class CouponsHistoryReq {
    private String name;
    private int comboId;
    private int productId;
    private int isSingle;
    private Long oderNumber;
    private int isWeeklyCoupons;

    public CouponsHistoryReq(String name, int comboId, int productId, int isSingle, Long oderNumber, int isWeeklyCoupons) {
        this.name = name;
        this.comboId = comboId;
        this.productId = productId;
        this.isSingle = isSingle;
        this.oderNumber = oderNumber;
        this.isWeeklyCoupons = isWeeklyCoupons;
    }

    public CouponsHistoryReq() {
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

    public int getIsSingle() {
        return isSingle;
    }

    public void setIsSingle(int isSingle) {
        this.isSingle = isSingle;
    }

    public Long getOderNumber() {
        return oderNumber;
    }

    public void setOderNumber(Long oderNumber) {
        this.oderNumber = oderNumber;
    }

    public int getIsWeeklyCoupons() {
        return isWeeklyCoupons;
    }

    public void setIsWeeklyCoupons(int isWeeklyCoupons) {
        this.isWeeklyCoupons = isWeeklyCoupons;
    }
}
