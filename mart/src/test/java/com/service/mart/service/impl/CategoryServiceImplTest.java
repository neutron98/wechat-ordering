package com.service.mart.service.impl;

import com.service.mart.entity.CategoryInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {
    @Autowired
    private CategoryServiceImpl categoryService;
    @Test
    public void findOne() {
        CategoryInfo categoryInfo = categoryService.findOne(1);
        Assert.assertEquals(new Integer(1), categoryInfo.getCategoryId());
    }

    @Test
    public void findAll() {
        List<CategoryInfo> categoryInfoList = categoryService.findAll();
        Assert.assertNotEquals(0, categoryInfoList.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<CategoryInfo> categoryInfoList = categoryService.findByCategoryTypeIn(Arrays.asList(1,2,3,4));
        Assert.assertNotEquals(0, categoryInfoList.size());
    }

    @Test
    public void save() {
        CategoryInfo categoryInfo = new CategoryInfo("men's only", 10);
        CategoryInfo result = categoryService.save(categoryInfo);
        Assert.assertNotNull(result);
    }
}