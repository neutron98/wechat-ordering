package cmu.youchun.recommender.model;

import lombok.Data;

import java.util.Date;
@Data
public class CategoryModel {

    private Integer id;

    private Date createdTime;

    private Date updatedTime;

    private String name;

    private String iconUrl;

    private Integer priority;
    
}