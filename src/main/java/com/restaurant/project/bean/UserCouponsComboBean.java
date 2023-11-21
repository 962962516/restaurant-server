package com.restaurant.project.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("user_coupons_combo")
public class UserCouponsComboBean {
    @TableId
    private int userCouponsComboId;
    private Long userId;
    private int comboId;

    public UserCouponsComboBean(int userCouponsComboId, Long userId, int comboId) {
        this.userCouponsComboId = userCouponsComboId;
        this.userId = userId;
        this.comboId = comboId;
    }

    public UserCouponsComboBean() {
    }

    public int getUserCouponsComboId() {
        return userCouponsComboId;
    }

    public void setUserCouponsComboId(int userCouponsComboId) {
        this.userCouponsComboId = userCouponsComboId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getComboId() {
        return comboId;
    }

    public void setComboId(int comboId) {
        this.comboId = comboId;
    }
}
