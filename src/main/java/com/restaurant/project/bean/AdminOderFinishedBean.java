package com.restaurant.project.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("admin_oder_finished")
public class AdminOderFinishedBean {
    @TableId
    private int finishedId;
    private String name;
    private int number;
    private String oderTime;
    private int OderNumber;
    private int price;

    public AdminOderFinishedBean(int finishedId, String name, int number, String oderTime, int oderNumber, int price) {
        this.finishedId = finishedId;
        this.name = name;
        this.number = number;
        this.oderTime = oderTime;
        OderNumber = oderNumber;
        this.price = price;
    }

    public AdminOderFinishedBean() {
    }

    public int getFinishedId() {
        return finishedId;
    }

    public void setFinishedId(int finishedId) {
        this.finishedId = finishedId;
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
        return OderNumber;
    }

    public void setOderNumber(int oderNumber) {
        OderNumber = oderNumber;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
