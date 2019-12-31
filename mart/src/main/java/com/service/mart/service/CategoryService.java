package com.service.mart.service;

import com.service.mart.entity.CategoryInfo;

import java.util.List;

public interface CategoryService {
    CategoryInfo findOne(Integer categoryId);

    /** for providers */
    List<CategoryInfo> findAll();

    /** for consumers */
    List<CategoryInfo> findByCategoryTypeIn(List<Integer> categoryTypeList);

    /** save and update */
    CategoryInfo save(CategoryInfo categoryInfo);

}
