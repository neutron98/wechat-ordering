package com.service.mart.controller;

import com.service.mart.entity.CategoryInfo;
import com.service.mart.exception.SellException;
import com.service.mart.form.CategoryForm;
import com.service.mart.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
 * Seller Category
 */

@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map){
        List<CategoryInfo> categoryInfoList = categoryService.findAll();
        map.put("categoryList", categoryInfoList);
        return new ModelAndView("category/list", map);
    }

    @GetMapping("/edit")
    public ModelAndView edit(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                             Map<String, Object> map){
        if (categoryId != null){
            CategoryInfo categoryInfo = categoryService.findOne(categoryId);
            map.put("categoryInfo", categoryInfo);
        }
        return new ModelAndView("category/edit", map);

    }

    /**
     * Add a new or update a category.
     * @param form
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map){
        // validate form
        if (bindingResult.hasErrors()){
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/category/edit");
            return new ModelAndView("common/error", map);
        }

        CategoryInfo categoryInfo = new CategoryInfo();
        // copy properties from form to the categoryInfo object
        try{
            if (form.getCategoryId() != null){
                categoryInfo = categoryService.findOne(form.getCategoryId());
            }
            BeanUtils.copyProperties(form, categoryInfo);
            categoryService.save(categoryInfo);
        } catch (SellException e){
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/category/edit");
            return new ModelAndView("common/error", map);
        }
        // return to success page
        map.put("url", "/sell/seller/category/list");
        return new ModelAndView("common/success", map);


    }

}
