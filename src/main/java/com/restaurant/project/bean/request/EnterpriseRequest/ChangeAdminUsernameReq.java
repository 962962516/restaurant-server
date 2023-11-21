package com.restaurant.project.bean.request.EnterpriseRequest;

public class ChangeAdminUsernameReq {
    private Long userId;
    private String username;

    public ChangeAdminUsernameReq(Long userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public ChangeAdminUsernameReq() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
