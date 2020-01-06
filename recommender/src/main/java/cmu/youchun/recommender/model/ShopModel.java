package cmu.youchun.recommender.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class ShopModel {
    private Integer id;

    private Date createdTime;

    private Date updatedTime;

    private String name;

    private BigDecimal rating;

    private Integer pricePerConsumer;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private Integer distance;

    private Integer categoryId;

    private String tags;

    private String startTime;

    private String endTime;

    private String address;

    private Integer sellerId;

    private String iconUrl;

    private CategoryModel categoryModel;

    private SellerModel sellerModel;

}