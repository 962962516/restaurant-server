package com.restaurant.project.bean.response.EnterpriseResponse;

public class AllClassRsp {
    private String productName;
    private int volume;
    private int price;

    public AllClassRsp(String productName, int volume, int price) {
        this.productName = productName;
        this.volume = volume;
        this.price = price;
    }

    public AllClassRsp() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
