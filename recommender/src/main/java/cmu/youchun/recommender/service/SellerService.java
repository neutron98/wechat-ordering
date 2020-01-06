package cmu.youchun.recommender.service;

import cmu.youchun.recommender.BusinessException;
import cmu.youchun.recommender.model.SellerModel;

import java.util.List;

public interface SellerService {
    /**
     * Create a SellerModel
     * @param sellerModel
     * @return
     */
    SellerModel create(SellerModel sellerModel);

    /**
     * Select one seller by id.
     * @param id
     * @return
     */
    SellerModel get(Integer id);

    /**
     * Select all sellers.
     * @return
     */
    List<SellerModel> selectAll();

    /**
     * Change seller status.
     * @param id seller id
     * @return
     * @throws BusinessException
     */
    SellerModel changeStatus(Integer id) throws BusinessException;

    Integer countAll();
}
