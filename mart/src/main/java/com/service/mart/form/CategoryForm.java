package com.service.mart.form;

import lombok.Data;

@Data
public class CategoryForm {

    private Integer categoryId;
    /** Category Name*/
    private String categoryName;
    /** Category Number*/
    private Integer categoryType;

}
