package cmu.youchun.recommender.controller.admin;

import cmu.youchun.recommender.BusinessException;
import cmu.youchun.recommender.aspect.AdminPermission;
import cmu.youchun.recommender.common.CommonUtil;
import cmu.youchun.recommender.common.EmBusinessError;
import cmu.youchun.recommender.common.PageQuery;
import cmu.youchun.recommender.form.CategoryForm;
import cmu.youchun.recommender.model.CategoryModel;
import cmu.youchun.recommender.service.CategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller("/admin/category")
@RequestMapping("/admin/category")
public class AdminCategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * Display all categories by page.
     * @param pageQuery PageQuery with size and page parameters.
     * @return
     */
    @RequestMapping("/index")
    @AdminPermission
    public ModelAndView index(PageQuery pageQuery){
        PageHelper.startPage(pageQuery.getPage(),pageQuery.getSize());
        List<CategoryModel> categoryModelList = categoryService.selectAll();
        PageInfo<CategoryModel> categoryModelPageInfo = new PageInfo<>(categoryModelList);
        ModelAndView modelAndView = new ModelAndView("/admin/category/index.html");
        modelAndView.addObject("data",categoryModelPageInfo);
        modelAndView.addObject("CONTROLLER_NAME","category");
        modelAndView.addObject("ACTION_NAME","index");
        return modelAndView;
    }

    /**
     * Redirect the admin to create page.
     * @return
     */
    @RequestMapping("/createpage")
    @AdminPermission
    public ModelAndView createPage(){
        ModelAndView modelAndView = new ModelAndView("/admin/category/create.html");
        modelAndView.addObject("CONTROLLER_NAME","category");
        modelAndView.addObject("ACTION_NAME","create");
        return modelAndView;
    }

    @PostMapping("/create")
    @AdminPermission
    public String create(@Valid CategoryForm categoryForm, BindingResult bindingResult) throws BusinessException {
        if(bindingResult.hasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }

        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setName(categoryForm.getName());
        categoryModel.setIconUrl(categoryForm.getIconUrl());
        categoryModel.setPriority(categoryForm.getPriority());

        categoryService.create(categoryModel);

        return "redirect:/admin/category/index";


    }

}
