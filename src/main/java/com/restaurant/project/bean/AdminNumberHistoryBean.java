package com.restaurant.project.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("admin_number_history")
public class AdminNumberHistoryBean {
    @TableId
    private int admin_number_history_id;
    private Long adminId;
    private int numberChanges;
    private int productId;
    private String time;

    public AdminNumberHistoryBean(int admin_number_history_id, Long adminId, int numberChanges, int productId, String time) {
        this.admin_number_history_id = admin_number_history_id;
        this.adminId = adminId;
        this.numberChanges = numberChanges;
        this.productId = productId;
        this.time = time;
    }

    public AdminNumberHistoryBean() {
    }

    public int getAdmin_number_history_id() {
        return admin_number_history_id;
    }

    public void setAdmin_number_history_id(int admin_number_history_id) {
        this.admin_number_history_id = admin_number_history_id;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public int getNumberChanges() {
        return numberChanges;
    }

    public void setNumberChanges(int numberChanges) {
        this.numberChanges = numberChanges;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
