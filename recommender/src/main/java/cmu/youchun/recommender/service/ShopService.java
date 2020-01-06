package cmu.youchun.recommender.service;

import cmu.youchun.recommender.BusinessException;
import cmu.youchun.recommender.model.ShopModel;

import java.math.BigDecimal;
import java.util.List;

public interface ShopService {

    /**
     * Create a store.
     */
    ShopModel create(ShopModel shopModel) throws BusinessException;

    /**
     * Select one store.
     */
    ShopModel get(Integer id);

    /**
     * Select all stores.
     */
    List<ShopModel> selectAll();

    /**
     * Count all shops.
     */
    Integer countAll();

    /**
     * Recommend
     */

    List<ShopModel> recommend(BigDecimal longitude, BigDecimal latitude);
}
