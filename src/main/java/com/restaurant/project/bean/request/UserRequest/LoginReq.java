package com.restaurant.project.bean.request.UserRequest;

public class LoginReq {
    private String username;
    private String password;

    private String userInfo;

    public LoginReq(String username, String password, String userInfo) {
        this.username = username;
        this.password = password;
        this.userInfo = userInfo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }
}
