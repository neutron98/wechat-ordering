package cmu.youchun.recommender.service.impl;

import cmu.youchun.recommender.BusinessException;
import cmu.youchun.recommender.common.EmBusinessError;
import cmu.youchun.recommender.dao.CategoryModelMapper;
import cmu.youchun.recommender.model.CategoryModel;
import cmu.youchun.recommender.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryModelMapper categoryModelMapper;

    @Override
    @Transactional
    public CategoryModel create(CategoryModel categoryModel) throws BusinessException {

        try {
            categoryModelMapper.insertSelective(categoryModel);
        } catch (DuplicateKeyException ex) {
            throw new BusinessException(EmBusinessError.CATEGORY_NAME_DUPLICATED);
        }

        return get(categoryModel.getId());
    }

    @Override
    public CategoryModel get(Integer id) {
        return categoryModelMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CategoryModel> selectAll() {
        return categoryModelMapper.selectAll();
    }

    @Override
    public Integer countAll() {
        return categoryModelMapper.countAll();
    }

}