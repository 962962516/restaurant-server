package com.restaurant.project.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("combo_product")
public class ComboProductBean {
    @TableId
    private int comboProductId;
    private int comboId;
    private int productId;
    private int quantity;

    public ComboProductBean(int comboProductId, int comboId, int productId, int quantity) {
        this.comboProductId = comboProductId;
        this.comboId = comboId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public ComboProductBean() {
    }

    public int getComboProductId() {
        return comboProductId;
    }

    public void setComboProductId(int comboProductId) {
        this.comboProductId = comboProductId;
    }

    public int getComboId() {
        return comboId;
    }

    public void setComboId(int comboId) {
        this.comboId = comboId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
