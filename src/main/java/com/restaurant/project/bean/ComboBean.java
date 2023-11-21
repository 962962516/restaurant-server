package com.restaurant.project.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("combo")
public class ComboBean {
    @TableId
    private int comboId;
    private String productName;
    private int productNumber;
    private String description;
    private String imgUrl;
    private int price;
    private int isHot;

    public ComboBean(int comboId, String productName, int productNumber, String description, String imgUrl, int price, int isHot) {
        this.comboId = comboId;
        this.productName = productName;
        this.productNumber = productNumber;
        this.description = description;
        this.imgUrl = imgUrl;
        this.price = price;
        this.isHot = isHot;
    }

    public ComboBean() {
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
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
