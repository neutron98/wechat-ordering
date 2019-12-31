package com.service.mart.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.service.mart.entity.OrderDetail;
import com.service.mart.enums.OrderStatusEnum;
import com.service.mart.enums.PayStatusEnum;
import com.service.mart.util.EnumUtil;
import com.service.mart.util.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Data transform object.
 */
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
    /** order id */
    private String orderId;
    /** consumer's first name */
    private String firstName;
    /** consumer's first name */
    private String lastName;
    /** consumer's phone number */
    private String consumerPhone;
    /** consumer's address */
    private String consumerAddress;
    /** openid of wechat */
    private String consumerOpenid;
    /** order's total price */
    private BigDecimal orderAmount;
    /** order status */
    private Integer orderStatus;
    /** payment status */
    private Integer payStatus;
    /**
     * Time properties will be used for sorting.
     */
    /** order create time */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    /** order update time*/
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    List<OrderDetail> orderDetailList;

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
    }
    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getByCode(payStatus, PayStatusEnum.class);
    }

}
