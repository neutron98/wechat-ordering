package com.service.mart.repository;

import com.service.mart.entity.CategoryInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryInfoRepositoryTest {
    @Autowired
    private ProductCategoryRepository repository;
    @Test
    public void findOneTest(){
        CategoryInfo categoryInfo =  repository.findOne(1);
        System.out.println(categoryInfo);
    }

    @Test
    @Transactional
    public void saveTest(){
        CategoryInfo categoryInfo = new CategoryInfo("women's favorite", 4);
        CategoryInfo result = repository.save(categoryInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> list = Arrays.asList(2, 3, 4);
        List<CategoryInfo> result = repository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0, result.size());
    }
}