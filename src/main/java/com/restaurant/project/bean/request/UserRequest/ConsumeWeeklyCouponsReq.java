package com.restaurant.project.bean.request.UserRequest;

public class ConsumeWeeklyCouponsReq {
    private Long weeklyCouponsId;
    private int isSingle;

    public ConsumeWeeklyCouponsReq(Long weeklyCouponsId, int isSingle) {
        this.weeklyCouponsId = weeklyCouponsId;
        this.isSingle = isSingle;
    }

    public ConsumeWeeklyCouponsReq() {
    }

    public Long getWeeklyCouponsId() {
        return weeklyCouponsId;
    }

    public void setWeeklyCouponsId(Long weeklyCouponsId) {
        this.weeklyCouponsId = weeklyCouponsId;
    }

    public int getIsSingle() {
        return isSingle;
    }

    public void setIsSingle(int isSingle) {
        this.isSingle = isSingle;
    }
}
