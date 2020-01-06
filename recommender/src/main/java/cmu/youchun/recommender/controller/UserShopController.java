package cmu.youchun.recommender.controller;

import cmu.youchun.recommender.BusinessException;
import cmu.youchun.recommender.common.CommonRes;
import cmu.youchun.recommender.common.EmBusinessError;
import cmu.youchun.recommender.model.ShopModel;
import cmu.youchun.recommender.service.CategoryService;
import cmu.youchun.recommender.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

@Controller("/shop")
@RequestMapping("/shop")
public class UserShopController {
    @Autowired
    private ShopService shopService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/recommend")
    @ResponseBody
    public CommonRes recommend(@RequestParam("longitude")BigDecimal longitude,
                               @RequestParam("latitude") BigDecimal latitude) throws BusinessException {
        if (longitude == null || latitude == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        List<ShopModel> shopModelList = shopService.recommend(longitude, latitude);
        return CommonRes.create(shopModelList);
    }
}
