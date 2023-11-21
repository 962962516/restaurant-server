package com.restaurant.project.bean.request.UserRequest;

public class RegisterReq {
    private String username;
    private String password;
    private int userInfo;

    public RegisterReq(String username, String password, int userInfo) {
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

    public int getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(int userInfo) {
        this.userInfo = userInfo;
    }
}
