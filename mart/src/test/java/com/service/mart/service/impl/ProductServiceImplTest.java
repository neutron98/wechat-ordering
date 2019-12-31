package com.service.mart.service.impl;

import com.service.mart.entity.ProductInfo;
import com.service.mart.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {
    @Autowired
    private ProductServiceImpl productService;
    @Test
    public void findOne() {
        ProductInfo productInfo = productService.findOne("123456");
        Assert.assertEquals("123456", productInfo.getProductId());
    }

    @Test
    public void findInAll() {
        List<ProductInfo> productInfoList = productService.findInAll();
        Assert.assertNotEquals(0, productInfoList.size());
    }

    @Test
    public void findAll() {
        PageRequest request = new PageRequest(0, 2);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        System.out.println(productInfoPage.getTotalElements());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123457");
        productInfo.setProductName("coke");
        productInfo.setProductPrice(new BigDecimal(1.00));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("normal coke");
        productInfo.setProductIcon("http://xxxxx.jpg");
        productInfo.setProductStatus(ProductStatusEnum.DIS.getCode());
        productInfo.setCategoryType(2);

        ProductInfo result = productService.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void onMarket(){
        ProductInfo result = productService.onMarket("123456");
        Assert.assertEquals(ProductStatusEnum.ON.getCode(), result.getProductStatus());
    }

    @Test
    public void discontinue(){
        ProductInfo result = productService.discontinue("123456");
        Assert.assertEquals(ProductStatusEnum.DIS.getCode(), result.getProductStatus());
    }
}