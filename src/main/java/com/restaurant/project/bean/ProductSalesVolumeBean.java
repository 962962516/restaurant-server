package com.restaurant.project.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("product_sales_volume")
public class ProductSalesVolumeBean {
    @TableId
    private int salesVolumeId;
    private String productName;
    private int salesVolume;

    public ProductSalesVolumeBean(int salesVolumeId, String productName, int salesVolume) {
        this.salesVolumeId = salesVolumeId;
        this.productName = productName;
        this.salesVolume = salesVolume;
    }

    public ProductSalesVolumeBean() {
    }

    public int getSalesVolumeId() {
        return salesVolumeId;
    }

    public void setSalesVolumeId(int salesVolumeId) {
        this.salesVolumeId = salesVolumeId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(int salesVolume) {
        this.salesVolume = salesVolume;
    }
}
