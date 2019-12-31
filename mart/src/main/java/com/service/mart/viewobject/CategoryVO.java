package com.service.mart.viewobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Product (including category)
 */
@Data
public class CategoryVO implements Serializable {


    private static final long serialVersionUID = -1566448480041402216L;
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private  Integer categoryType;

    @JsonProperty("foods")
    // returning all data to front-end might be unsafe, some information such as stock should not be revealed
    // to competitors. so we are not using ProductInfo object here.
    private List<ProductInfoVO> productInfoViewObjectList;
}
