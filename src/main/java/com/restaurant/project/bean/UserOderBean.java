package com.restaurant.project.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("user_oder_history")
public class UserOderBean {
    @TableId
    private int oderId;
    private Long userId;
    private String name;
    private int number;
    private String oderTime;
    private int oderNumber;

    public UserOderBean(int oderId, Long userId, String name, int number, String oderTime, int oderNumber) {
        this.oderId = oderId;
        this.userId = userId;
        this.name = name;
        this.number = number;
        this.oderTime = oderTime;
        this.oderNumber = oderNumber;
    }

    public UserOderBean() {
    }

    public int getOderId() {
        return oderId;
    }

    public void setOderId(int oderId) {
        this.oderId = oderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getOderTime() {
        return oderTime;
    }

    public void setOderTime(String oderTime) {
        this.oderTime = oderTime;
    }

    public int getOderNumber() {
        return oderNumber;
    }

    public void setOderNumber(int oderNumber) {
        this.oderNumber = oderNumber;
    }
}