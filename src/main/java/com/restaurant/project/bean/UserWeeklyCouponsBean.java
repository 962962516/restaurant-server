package com.restaurant.project.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("user_weekly_coupons")
public class UserWeeklyCouponsBean {
    @TableId
    private int userWeeklyCouponsId;
    private Long userId;
    private Long weeklyCouponsId;

    public UserWeeklyCouponsBean(int userWeeklyCouponsId, Long userId, Long weeklyCouponsId) {
        this.userWeeklyCouponsId = userWeeklyCouponsId;
        this.userId = userId;
        this.weeklyCouponsId = weeklyCouponsId;
    }

    public UserWeeklyCouponsBean() {
    }

    public int getUserWeeklyCouponsId() {
        return userWeeklyCouponsId;
    }

    public void setUserWeeklyCouponsId(int userWeeklyCouponsId) {
        this.userWeeklyCouponsId = userWeeklyCouponsId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getWeeklyCouponsId() {
        return weeklyCouponsId;
    }

    public void setWeeklyCouponsId(Long weeklyCouponsId) {
        this.weeklyCouponsId = weeklyCouponsId;
    }
}
