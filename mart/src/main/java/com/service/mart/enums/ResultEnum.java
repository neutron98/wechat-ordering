package com.service.mart.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    SUCCESS(0, "success"),
    PARAM_ERROR(1, "Invalid parameters"),

    PRODUCT_NOT_EXIST(10, "Product does not exist"),
    PRODUCT_STOCK_ERROR(11, "Invalid stock quantity"),
    ORDER_NOT_EXIST(12, "Order does not exist"),
    ORDER_DETAIL_NOT_EXIST(13, "Order detail does not exist"),
    ORDER_STATUS_ERROR(14, "Invalid order status"),
    ORDER_UPDATE_FAIL(15, "Order update failed"),
    ORDER_DETAIL_EMPTY(16, "Order detail is empty"),
    PAY_STATUS_ERROR(17, "Invalid payment status"),
    CART_EMPTY(18, "Cart can not be empty"),
    ORDER_OWNER_ERROR(19, "The order does not belong to current user"),
    WECHAT_MP_ERROR(20, "Error from WeChat Public Account"),
    WXPAY_NOTIFY_MONEY_VERIFY_ERROR(21, "Wrong amount in WeChat pay asynchronous notification"),
    ORDER_CANCEL_SUCCESS(22,"You have successfully canceled the order"),
    ORDER_FINISH_SUCCESS(23,"You have successfully finished the order"),
    PRODUCT_STATUS_ERROR(24, "Invalid product status"),
    LOGIN_FAILED(25, "Login failed: wrong login message"),
    LOGOUT_SUCCESS(26, "You have successfully logout")

    ;

    private  Integer code;
    private  String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
