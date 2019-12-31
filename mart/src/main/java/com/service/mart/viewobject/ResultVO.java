package com.service.mart.viewobject;

import lombok.Data;

import java.io.Serializable;

@Data
//为什么泛型有时候不用声明里面的类型？
//序列化？
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 4212656437106757655L;
    /** error code*/
    private Integer code;
    /** error message*/
    private String msg;
    private T data;
}
