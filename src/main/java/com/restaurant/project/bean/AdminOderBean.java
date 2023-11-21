package com.restaurant.project.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("admin_oder")
public class AdminOderBean {
    @TableId
    private int adminOderId;
    private String name;
    private int number;
    private String oderTime;
    private int oderNumber;
    private int price;
    private int isFinished;

    public AdminOderBean(int adminOderId, String name, int number, String oderTime, int oderNumber, int price, int isFinished) {
        this.adminOderId = adminOderId;
        this.name = name;
        this.number = number;
        this.oderTime = oderTime;
        this.oderNumber = oderNumber;
        this.price = price;
        this.isFinished = isFinished;
    }

    public AdminOderBean() {
    }

    public int getAdminOderId() {
        return adminOderId;
    }

    public void setAdminOderId(int adminOderId) {
        this.adminOderId = adminOderId;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(int isFinished) {
        this.isFinished = isFinished;
    }
}
