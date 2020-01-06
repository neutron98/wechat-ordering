package cmu.youchun.recommender.service.impl;

import cmu.youchun.recommender.BusinessException;
import cmu.youchun.recommender.common.EmBusinessError;
import cmu.youchun.recommender.dao.SellerModelMapper;
import cmu.youchun.recommender.model.SellerModel;
import cmu.youchun.recommender.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    SellerModelMapper sellerModelMapper;

    @Override
    public SellerModel create(SellerModel sellerModel) {
        int id = sellerModelMapper.insertSelective(sellerModel);
        return get(id);
    }

    @Override
    public SellerModel get(Integer id) {
        return sellerModelMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SellerModel> selectAll() {
        return sellerModelMapper.selectAll();
    }

    @Override
    public SellerModel changeStatus(Integer id) throws BusinessException {
        SellerModel sellerModel = get(id);
        if(sellerModel == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        Integer currentFlag = sellerModel.getDisabledFlag();
        Integer newFlag = 1 - currentFlag;
        sellerModel.setDisabledFlag(newFlag);
        sellerModelMapper.updateByPrimaryKeySelective(sellerModel);
        return sellerModel;
    }

    @Override
    public Integer countAll() {
        return sellerModelMapper.countAll();
    }
}
