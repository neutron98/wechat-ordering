package com.service.mart.service.impl;

import com.service.mart.entity.CategoryInfo;
import com.service.mart.repository.ProductCategoryRepository;
import com.service.mart.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private ProductCategoryRepository repository;

    @Override
    public CategoryInfo findOne(Integer categoryId) {
        return repository.findOne(categoryId);
    }

    @Override
    public List<CategoryInfo> findAll() {
        return repository.findAll();
    }

    @Override
    public List<CategoryInfo> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return repository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public CategoryInfo save(CategoryInfo categoryInfo) {
        return repository.save(categoryInfo);
    }
}
