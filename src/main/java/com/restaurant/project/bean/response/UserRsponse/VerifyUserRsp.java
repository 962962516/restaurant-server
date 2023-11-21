package com.restaurant.project.bean.response.UserRsponse;

public class VerifyUserRsp {
    private boolean isTimeOut;
    private int userInfo;

    public VerifyUserRsp(boolean isTimeOut, int userInfo) {
        this.isTimeOut = isTimeOut;
        this.userInfo = userInfo;
    }

    public VerifyUserRsp() {
    }

    public boolean isTimeOut() {
        return isTimeOut;
    }

    public void setTimeOut(boolean timeOut) {
        isTimeOut = timeOut;
    }

    public int getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(int userInfo) {
        this.userInfo = userInfo;
    }
}
