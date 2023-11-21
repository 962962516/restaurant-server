package com.restaurant.project.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("month_sales")
public class MonthSalesBean {
    @TableId
    private int monthSalesId;
    private String month;
    private int price;

    public MonthSalesBean(int monthSalesId, String month, int price) {
        this.monthSalesId = monthSalesId;
        this.month = month;
        this.price = price;
    }

    public MonthSalesBean() {
    }

    public int getMonthSaleId() {
        return monthSalesId;
    }

    public void setMonthSaleId(int monthSaleId) {
        this.monthSalesId = monthSaleId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
