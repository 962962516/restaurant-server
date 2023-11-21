package com.restaurant.project.bean.request.AdminRequest;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public class ReviseProductNumberReq {
    private int productId;
    private int Number;

    public ReviseProductNumberReq(int productId, int number) {
        this.productId = productId;
        Number = number;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }
}
