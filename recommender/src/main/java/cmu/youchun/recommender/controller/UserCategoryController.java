package cmu.youchun.recommender.controller;

import cmu.youchun.recommender.common.CommonRes;
import cmu.youchun.recommender.model.CategoryModel;
import cmu.youchun.recommender.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("/category")
@RequestMapping("/category")
public class UserCategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * Get all categories
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public CommonRes list(){
        List<CategoryModel> categoryModelList = categoryService.selectAll();
        return CommonRes.create(categoryModelList);

    }
}
