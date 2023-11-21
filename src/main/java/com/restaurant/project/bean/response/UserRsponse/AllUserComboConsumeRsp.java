package com.restaurant.project.bean.response.UserRsponse;

import java.util.Date;

public class AllUserComboConsumeRsp {
    private String name;
    private Long oderNumber;
    private String oderTime;


    public AllUserComboConsumeRsp() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOderNumber() {
        return oderNumber;
    }

    public void setOderNumber(Long oderNumber) {
        this.oderNumber = oderNumber;
    }

    public String getOderTime() {
        return oderTime;
    }

    public void setOderTime(String oderTime) {
        this.oderTime = oderTime;
    }
}
