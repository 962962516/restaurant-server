package com.restaurant.project.bean.request.UserRequest;

import java.util.Map;

public class PayForOderReq {
    private ProductCounts productCounts;
    private Integer oderNumber;

    public PayForOderReq(ProductCounts productCounts, Integer oderNumber) {
        this.productCounts = productCounts;
        this.oderNumber = oderNumber;
    }

    public PayForOderReq() {
    }

    public ProductCounts getProductCounts() {
        return productCounts;
    }

    public void setProductCounts(ProductCounts productCounts) {
        this.productCounts = productCounts;
    }

    public Integer getOderNumber() {
        return oderNumber;
    }

    public void setOderNumber(Integer oderNumber) {
        this.oderNumber = oderNumber;
    }

    public static class ProductCounts {
        private Map<String, Integer> value;

        public Map<String, Integer> getValue() {
            return value;
        }

        public void setValue(Map<String, Integer> value) {
            this.value = value;
        }
    }
}

