package com.restaurant.project.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("user_Service_Rating")
public class UserServiceRatingBean {
    @TableId
    private int id;
    private Long userId;
    private String raClass;
    private int rating;
    private String reaction;

    public UserServiceRatingBean(int id, Long userId, String raClass, int rating, String reaction) {
        this.id = id;
        this.userId = userId;
        this.raClass = raClass;
        this.rating = rating;
        this.reaction = reaction;
    }

    public UserServiceRatingBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRaClass() {
        return raClass;
    }

    public void setRaClass(String raClass) {
        this.raClass = raClass;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReaction() {
        return reaction;
    }

    public void setReaction(String reaction) {
        this.reaction = reaction;
    }
}
