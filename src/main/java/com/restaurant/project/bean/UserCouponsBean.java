package com.restaurant.project.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("user_coupons")
public class UserCouponsBean {
    @TableId
    private int userCouponsId;
    private Long userId;
    private int couponId;

    public UserCouponsBean(Long userId, int couponId) {
        this.userId = userId;
        this.couponId = couponId;
    }

    public UserCouponsBean() {
    }

    public int getUserCouponsId() {
        return userCouponsId;
    }

    public void setUserCouponsId(int userCouponsId) {
        this.userCouponsId = userCouponsId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }
}
