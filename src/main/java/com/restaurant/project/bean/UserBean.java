package com.restaurant.project.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("user")
public class UserBean {
    @TableId
    private String userId;
    private String username;
    private String password;
    private String email;
    private String userPhone;
    private int isVip;
    private int userInfo;

    public UserBean(String userId, String username, String password, String email, String userPhone, int isVip, int userInfo) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.userPhone = userPhone;
        this.isVip = isVip;
        this.userInfo = userInfo;
    }

    public UserBean() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public int getIsVip() {
        return isVip;
    }

    public void setIsVip(int isVip) {
        this.isVip = isVip;
    }

    public int getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(int userInfo) {
        this.userInfo = userInfo;
    }
}
