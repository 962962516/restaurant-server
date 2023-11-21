package com.restaurant.project.bean.request.UserRequest;

public class ConsumeComboIntegralReq {
    private int comboId;
    private int integral;

    public ConsumeComboIntegralReq(int comboId, int integral) {
        this.comboId = comboId;
        this.integral = integral;
    }

    public ConsumeComboIntegralReq() {
    }

    public int getComboId() {
        return comboId;
    }

    public void setComboId(int comboId) {
        this.comboId = comboId;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }
}
