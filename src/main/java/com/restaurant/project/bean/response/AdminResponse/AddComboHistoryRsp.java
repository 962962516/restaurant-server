package com.restaurant.project.bean.response.AdminResponse;

public class AddComboHistoryRsp {
    private Long adminId;
    private int numberChanges;
    private String productName;
    private String time;

    public AddComboHistoryRsp(Long adminId, int numberChanges, String productName, String time) {
        this.adminId = adminId;
        this.numberChanges = numberChanges;
        this.productName = productName;
        this.time = time;
    }

    public AddComboHistoryRsp() {
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
