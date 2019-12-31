package com.service.mart.entity;

import com.service.mart.enums.OrderStatusEnum;
import com.service.mart.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class OrderMaster {
    @Id
    /** order id */
    private String orderId;

    /** consumer's first name */
    private String firstName;

    /** consumer's first name */
    private String lastName;

    /** consumer's phone number */
    private  String consumerPhone;

    /** consumer's address */
    private  String consumerAddress;

    /** openid in WeChat */
    private String consumerOpenid;

    /** order's total price */
    private BigDecimal orderAmount;

    /** order status, in default: 0 - new order */
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /** payment status, in default: 0 - not paid */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    /**
     * Time properties will be used for sorting.
     */
    /** order create time */
    private Date createTime;

    /** order update time*/
    private Date updateTime;
}
