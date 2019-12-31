package com.service.mart.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@DynamicUpdate
// will generate getters and setters when compiling, so will not affect performance
@Data
public class CategoryInfo {
    /** Category ID*/
    @Id
    @GeneratedValue
    private Integer categoryId;
    /** Category Name*/
    private String categoryName;
    /** Category Number*/
    private Integer categoryType;

    /** create time */
    private Date createTime;
    /** update time */
    private Date updateTime;

    public CategoryInfo() {
    }

    public CategoryInfo(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
