package com.service.mart.enums;

import lombok.Getter;

/**
 * Product Status
 */

@Getter
public enum ProductStatusEnum  implements CodeEnum{
    ON(0,"on the market"),
    DIS(1, "discontinued");

    private Integer code;
    private String message;
    ProductStatusEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
