package com.restaurant.project.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.ibatis.annotations.Param;

@TableName("admin_add_combo_history")
public class AdminAddComboHistoryBean {
    @TableId
    private int addComboHistoryId;
    private Long adminId;
    private String productName;
    private String time;

    public AdminAddComboHistoryBean(int addComboHistoryId, Long adminId, String productName, String time) {
        this.addComboHistoryId = addComboHistoryId;
        this.adminId = adminId;
        this.productName = productName;
        this.time = time;
    }

    public AdminAddComboHistoryBean() {
    }

    public int getAddComboHistoryId() {
        return addComboHistoryId;
    }

    public void setAddComboHistoryId(int addComboHistoryId) {
        this.addComboHistoryId = addComboHistoryId;
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
