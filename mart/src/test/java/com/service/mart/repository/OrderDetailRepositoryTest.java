package com.service.mart.repository;

import com.service.mart.entity.OrderDetail;
import org.hibernate.criterion.Order;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {
    @Autowired
    private OrderDetailRepository repository;
    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("123456780");
        orderDetail.setOrderId("333333");
        orderDetail.setProductId("222222");
        orderDetail.setProductName("apple");
        orderDetail.setProductPrice(new BigDecimal("1.2"));
        orderDetail.setProductQuantity(3);

        OrderDetail result = repository.save(orderDetail);
        Assert.assertNotNull(result);

    }
    @Test
    public void findByOrderId() {
        List<OrderDetail> orderDetailList = repository.findByOrderId("111111");
        Assert.assertNotEquals(0, orderDetailList.size());
    }
}