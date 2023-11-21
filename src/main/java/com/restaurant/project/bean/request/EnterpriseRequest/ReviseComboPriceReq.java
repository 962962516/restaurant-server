package com.restaurant.project.bean.request.EnterpriseRequest;

public class ReviseComboPriceReq {
    private int comboId;
    private int price;

    public ReviseComboPriceReq(int comboId, int price) {
        this.comboId = comboId;
        this.price = price;
    }

    public ReviseComboPriceReq() {
    }

    public int getComboId() {
        return comboId;
    }

    public void setComboId(int comboId) {
        this.comboId = comboId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
