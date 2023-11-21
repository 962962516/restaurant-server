package com.restaurant.project.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.Column;

@TableName("product")
public class ProductBean {
    @TableId
    private int productId;
    private String productName;
    private String description;
    private Double price;
    private int productCount;
    private String imgUrl;
    private int adiseListNo;
    private int isHot;

    public ProductBean() {
    }

    public ProductBean(int productId, String productName, String description, Double price, int productCount, String imgUrl, int adiseListNo, int isHot) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.productCount = productCount;
        this.imgUrl = imgUrl;
        this.adiseListNo = adiseListNo;
        this.isHot = isHot;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getAdiseListNo() {
        return adiseListNo;
    }

    public void setAdiseListNo(int adiseListNo) {
        this.adiseListNo = adiseListNo;
    }

    public int getIsHot() {
        return isHot;
    }

    public void setIsHot(int isHot) {
        this.isHot = isHot;
    }
}
