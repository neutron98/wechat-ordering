package cmu.youchun.recommender.service;

import cmu.youchun.recommender.BusinessException;
import cmu.youchun.recommender.model.CategoryModel;

import java.util.List;

public interface CategoryService {
    CategoryModel create(CategoryModel categoryModel) throws BusinessException;
    CategoryModel get(Integer id);
    List<CategoryModel> selectAll();

    Integer countAll();

}
