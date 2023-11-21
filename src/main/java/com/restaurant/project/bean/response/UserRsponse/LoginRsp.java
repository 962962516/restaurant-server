package com.restaurant.project.bean.response.UserRsponse;

import com.restaurant.project.bean.UserBean;

public class LoginRsp {
    private String token;
    private UserBean userInfo;

    public LoginRsp(String token, UserBean userInfo) {
        this.token = token;
        this.userInfo = userInfo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserBean userInfo) {
        this.userInfo = userInfo;
    }
}
