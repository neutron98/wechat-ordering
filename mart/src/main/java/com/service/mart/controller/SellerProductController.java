package com.service.mart.controller;

import com.service.mart.entity.CategoryInfo;
import com.service.mart.entity.ProductInfo;
import com.service.mart.exception.SellException;
import com.service.mart.form.ProductForm;
import com.service.mart.service.CategoryService;
import com.service.mart.service.ProductService;
import com.service.mart.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Controller
@RequestMapping("/seller/product")
@Slf4j
public class SellerProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") int page,
                             @RequestParam(value = "size", defaultValue = "10") int size,
                             Map<String, Object> map){
        PageRequest request = new PageRequest(page - 1, size);  // start from 1
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("product/list", map);
    }

    @GetMapping("/on_market")
    public ModelAndView onMarket(@RequestParam("productId") String productId,
                               Map<String, Object> map){
        try{
            productService.onMarket(productId);
        } catch(SellException e){
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/discontinue")
    public ModelAndView discontinue(@RequestParam("productId") String productId,
                                 Map<String, Object> map){
        try{
            productService.discontinue(productId);
        } catch(SellException e){
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/edit")
    public ModelAndView edit(@RequestParam(value = "productId", required = false) String productId,
                              Map<String, Object> map){
        if (!StringUtils.isEmpty(productId)){
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo", productInfo);
        }

        // search all categories
        List<CategoryInfo> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);

        return new ModelAndView("product/edit", map);
    }

    /**
     * Submit/ Update a product's info
     * @param form
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    //@CachePut(cacheNames = "product", key = "123")
    //@CacheEvict(cacheNames = "product", key = "123")
    public ModelAndView save(@Valid ProductForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map){
        // validate form
        if (bindingResult.hasErrors()){
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/product/edit");
            return new ModelAndView("common/error", map);
        }
        // copy properties from form to the productInfo object
        ProductInfo productInfo = new ProductInfo();
        try{
            if (!StringUtils.isEmpty(form.getProductId())) {
                productInfo = productService.findOne(form.getProductId());
            } else{
                form.setProductId(KeyUtil.genUniqueKey());
            }
            BeanUtils.copyProperties(form, productInfo);
            productService.save(productInfo);
        } catch (SellException e){
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/edit");
            return new ModelAndView("common/error", map);
        }
        // return to success page
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }



}
