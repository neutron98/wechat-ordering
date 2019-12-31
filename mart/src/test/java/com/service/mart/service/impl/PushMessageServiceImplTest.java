package com.service.mart.service.impl;

import com.service.mart.dto.OrderDTO;
import com.service.mart.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PushMessageServiceImplTest {
    @Autowired
    private PushMessageServiceImpl pushMessageService;

    @Autowired
    private OrderService orderService;



    @Test
    public void orderStatus() {
        OrderDTO orderDTO = orderService.findOne("157739780175785417");
        pushMessageService.orderStatus(orderDTO);
    }
}