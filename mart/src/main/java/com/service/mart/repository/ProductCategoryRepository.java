package com.service.mart.repository;

import com.service.mart.entity.CategoryInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<CategoryInfo, Integer> {
    List<CategoryInfo> findByCategoryTypeIn(List<Integer> categoryTypeList);


}
