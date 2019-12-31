package com.service.mart.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum implements CodeEnum{
    WAIT(0, "waiting for payment"),
    SUCCESS(1, "payment successful");

    private Integer code;
    private String message;
    PayStatusEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
