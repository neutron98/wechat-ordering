package com.service.mart.form;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class ProductForm {
    private String productId;
    /** product name */
    private String productName;
    /** unit price*/
    private BigDecimal productPrice;
    /** stock*/
    private Integer productStock;
    /** description */
    private String productDescription;
    /** url of the image*/
    private String productIcon;
    /** category number */
    private Integer categoryType;
    /** create time */
    private Date createTime;
    /** update time */
    private Date updateTime;
}
