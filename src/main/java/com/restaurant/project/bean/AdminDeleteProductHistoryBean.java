package com.restaurant.project.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("admin_delete_product_history")
public class AdminDeleteProductHistoryBean {
    @TableId
    private int deleteProductHistoryId;
    private Long adminId;
    private String productName;
    private String time;

    public AdminDeleteProductHistoryBean(int deleteProductHistoryId, Long adminId, String productName, String time) {
        this.deleteProductHistoryId = deleteProductHistoryId;
        this.adminId = adminId;
        this.productName = productName;
        this.time = time;
    }

    public AdminDeleteProductHistoryBean() {
    }

    public int getDeleteProductHistoryId() {
        return deleteProductHistoryId;
    }

    public void setDeleteProductHistoryId(int deleteProductHistoryId) {
        this.deleteProductHistoryId = deleteProductHistoryId;
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
