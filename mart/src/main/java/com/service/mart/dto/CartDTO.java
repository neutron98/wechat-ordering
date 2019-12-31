package com.service.mart.dto;

import lombok.Data;

@Data
public class CartDTO {
    /** product Id */
    private String productId;
    /** product quantity */
    private  Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
