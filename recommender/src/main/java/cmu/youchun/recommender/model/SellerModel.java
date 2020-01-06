package cmu.youchun.recommender.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class SellerModel {

    private Integer id;

    private String name;

    private Date createdTime;

    private Date updatedTime;

    private BigDecimal rating = new BigDecimal("0");

    private Integer disabledFlag = new Integer(0);


}