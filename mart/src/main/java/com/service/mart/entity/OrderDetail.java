package com.service.mart.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class OrderDetail {
    @Id
    private String detailId;
    /** order id */
    private String orderId;
    /** product id */
    private  String productId;
    /** product name */
    private String productName;
    /** product unit price */
    private BigDecimal productPrice;
    /** purchased quantity */
    private Integer productQuantity;
}
