package com.restaurant.project.bean;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("admin_add_product_history")
public class AdminAddProductHistoryBean {
    private int addProductHistoryId;
    private Long adminId;
    private String productName;
    private String time;

    public AdminAddProductHistoryBean(int addProductHistoryId, Long adminId, String productName, String time) {
        this.addProductHistoryId = addProductHistoryId;
        this.adminId = adminId;
        this.productName = productName;
        this.time = time;
    }

    public AdminAddProductHistoryBean() {
    }

    public int getAddProductHistoryId() {
        return addProductHistoryId;
    }

    public void setAddProductHistoryId(int addProductHistoryId) {
        this.addProductHistoryId = addProductHistoryId;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
