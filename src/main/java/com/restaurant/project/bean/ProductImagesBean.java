package com.restaurant.project.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("product_images")
public class ProductImagesBean {
    @TableId
    private String imageId;
    private byte[] imageData;

    public ProductImagesBean(String imageId, byte[] imageData) {
        this.imageId = imageId;
        this.imageData = imageData;
    }

    public ProductImagesBean() {
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}
