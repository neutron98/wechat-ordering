package com.service.mart.controller;

import com.service.mart.entity.CategoryInfo;
import com.service.mart.entity.ProductInfo;
import com.service.mart.service.CategoryService;
import com.service.mart.service.ProductService;
import com.service.mart.util.ResultVOUtil;
import com.service.mart.viewobject.CategoryVO;
import com.service.mart.viewobject.ProductInfoVO;
import com.service.mart.viewobject.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class ConsumerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    @Cacheable(cacheNames = "productList", key = "234")
    public ResultVO list(){
        // 1. select all on-market products
        List<ProductInfo> productInfoList = productService.findInAll();

        // 2. select category (at one time)
        // use lambda to simplify for-loop (can even filter)
        List<Integer> categoryTypeList =  productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());

        // 3. data concatenation： add productVO
        List<CategoryVO> categoryVOList = getCategoryVO(categoryTypeList, productInfoList);

        // 4. add to ResultVO and return
        return ResultVOUtil.success(categoryVOList);
    }

    /**
     * Private helper method to get the category view object in JSON response.
     * @param categoryTypeList all the category types
     * @param productInfoList all the productInfo objects
     * @return a list of Category View Objects
     */
    private List<CategoryVO> getCategoryVO(List<Integer> categoryTypeList, List<ProductInfo> productInfoList){
        List<CategoryVO> categoryVOList = new LinkedList<>();
        // traverse the category list
        List<CategoryInfo> categoryInfoList = categoryService.findByCategoryTypeIn(categoryTypeList);
        for (CategoryInfo categoryInfo : categoryInfoList){ // for each category object
            CategoryVO categoryVO = new CategoryVO(); // initialize a new category view object
            // set the three properties
            categoryVO.setCategoryName(categoryInfo.getCategoryName());  // set category name
            categoryVO.setCategoryType(categoryInfo.getCategoryType());  // set category type
            List<ProductInfoVO> productInfoVOList = getProductInfoVOList(categoryInfo, productInfoList);
            categoryVO.setProductInfoViewObjectList(productInfoVOList); // set productInfo view objects list

            // add the category view object to the list
            categoryVOList.add(categoryVO);
        }
        return categoryVOList;
    }
    /**
     * Private helper method to get product view objects by category type.
     * @param categoryInfo the current categoryInfo object
     * @param productInfoList all the productInfo objects
     * @return a list of ProductInfo View Objects, whose category type matches the categoryInfo object's type.
     */
    private List<ProductInfoVO> getProductInfoVOList(CategoryInfo categoryInfo, List<ProductInfo> productInfoList){
        List<ProductInfoVO> productInfoVOList = new LinkedList<>();
        for (ProductInfo productInfo: productInfoList){
            if (productInfo.getCategoryType().equals(categoryInfo.getCategoryType())){ // if category matches
                //copy productInfo properties into productInfoVO
                ProductInfoVO productInfoVO = new ProductInfoVO();
                BeanUtils.copyProperties(productInfo, productInfoVO);
                // add the productInfoVO to the list
                productInfoVOList.add(productInfoVO);
            }
        }
        return productInfoVOList;
    }

}
