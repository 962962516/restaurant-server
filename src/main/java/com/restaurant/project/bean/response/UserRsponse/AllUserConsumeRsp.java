package com.restaurant.project.bean.response.UserRsponse;

import java.util.Date;

public class AllUserConsumeRsp {
    private String productName;
    private int productNumber;
    private String oderTime;
    private Long oderNumber;

    public AllUserConsumeRsp(String productName, int productNumber, String oderTime, Long oderNumber) {
        this.productName = productName;
        this.productNumber = productNumber;
        this.oderTime = oderTime;
        this.oderNumber = oderNumber;
    }

    public AllUserConsumeRsp() {
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

    public String getOderTime() {
        return oderTime;
    }

    public void setOderTime(String oderTime) {
        this.oderTime = oderTime;
    }

    public Long getOderNumber() {
        return oderNumber;
    }

    public void setOderNumber(Long oderNumber) {
        this.oderNumber = oderNumber;
    }
}
