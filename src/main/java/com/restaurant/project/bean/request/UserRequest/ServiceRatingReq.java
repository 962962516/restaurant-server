package com.restaurant.project.bean.request.UserRequest;

public class ServiceRatingReq {
    private String raClass;
    private int rating;
    private String reaction;

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
