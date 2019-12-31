package com.service.mart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.service.mart.enums.ProductStatusEnum;
import com.service.mart.util.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class ProductInfo implements Serializable {

    private static final long serialVersionUID = 4257848014699129317L;
    @Id
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
    /** status, 0=normal, 1=unavailable*/
    private Integer productStatus = ProductStatusEnum.ON.getCode();
    /** category number */
    private Integer categoryType;
    /** create time */
    private Date createTime;
    /** update time */
    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
    }

}
