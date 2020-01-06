package cmu.youchun.recommender.common;

import lombok.Data;

public enum EmBusinessError {

    /**
     * Common errors start with 10000
     */

    NO_OBJECT_FOUND(10001,"The requested object does not exist"),
    UNKNOWN_ERROR(10002,"Unknown error"),
    NO_HANDLER_FOUND(10003,"Could not find the handler for this path"),
    BIND_EXCEPTION_ERROR(10004,"Error request parameters"),
    PARAMETER_VALIDATION_ERROR(10005,"Request parameters validation failed"),

    /**
     * User service errors starts with 20000
     */

    REGISTER_DUP_FAIL(20001,"The user already exists"),

    LOGIN_FAIL(20002,"Wrong Phone number or password"),
    /**
     * Admin errors starts with 30000
     */
    ADMIN_SHOULD_LOGIN(30001,"Admin should login"),


    /**
     * Category Errors starts with 40000
     */
    CATEGORY_NAME_DUPLICATED(40001,"Category name already exists")

    ;

    /**
     * Error code.
     */
    private Integer errCode;
    /**
     * Error message.
     */
    private String errMsg;

    EmBusinessError(Integer errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

}