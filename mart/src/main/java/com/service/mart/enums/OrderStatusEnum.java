package com.service.mart.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum implements CodeEnum{
    NEW(0, "new order"),
    FINISHED(1, "finished"),
    CANCELLED(2, "cancelled");

    private Integer code;
    private String message;
    OrderStatusEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }


}
