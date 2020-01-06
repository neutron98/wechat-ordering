package cmu.youchun.recommender.service.impl;

import cmu.youchun.recommender.BusinessException;
import cmu.youchun.recommender.common.EmBusinessError;
import cmu.youchun.recommender.dao.ShopModelMapper;
import cmu.youchun.recommender.model.CategoryModel;
import cmu.youchun.recommender.model.SellerModel;
import cmu.youchun.recommender.model.ShopModel;
import cmu.youchun.recommender.service.CategoryService;
import cmu.youchun.recommender.service.SellerService;
import cmu.youchun.recommender.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopModelMapper shopModelMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SellerService sellerService;

    @Override
    @Transactional
    public ShopModel create(ShopModel shopModel) throws BusinessException {
        //Validate the seller
        SellerModel sellerModel = sellerService.get(shopModel.getSellerId());
        if (sellerModel == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "The seller does not exist");
        }

        if(sellerModel.getDisabledFlag().intValue() == 1){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"The seller was disabled");
        }

        // validate the category
        CategoryModel categoryModel = categoryService.get(shopModel.getCategoryId());
        if(categoryModel == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"The category does not exist");
        }
        int id = shopModelMapper.insertSelective(shopModel);
        return get(id);
    }

    @Override
    public ShopModel get(Integer id) {
        ShopModel storeModel = shopModelMapper.selectByPrimaryKey(id);
        if(storeModel == null){
            return null;
        }
        storeModel.setSellerModel(sellerService.get(storeModel.getSellerId()));
        storeModel.setCategoryModel(categoryService.get(storeModel.getCategoryId()));
        return storeModel;
    }

    @Override
    public List<ShopModel> selectAll() {
        List<ShopModel> shopModelList = shopModelMapper.selectAll();
        // set attributes to each object
        shopModelList.forEach(shopModel -> {
            shopModel.setSellerModel(sellerService.get(shopModel.getSellerId()));
            shopModel.setCategoryModel(categoryService.get(shopModel.getCategoryId()));
        });
        return shopModelList;
    }

    @Override
    public Integer countAll() {
        return shopModelMapper.countAll();
    }

    @Override
    public List<ShopModel> recommend(BigDecimal longitude, BigDecimal latitude) {
        List<ShopModel> shopModelList = shopModelMapper.recommend(longitude, latitude);

        shopModelList.forEach(shopModel -> {
            shopModel.setSellerModel(sellerService.get(shopModel.getSellerId()));
            shopModel.setCategoryModel(categoryService.get(shopModel.getCategoryId()));
        });

        return shopModelList;
    }
}
