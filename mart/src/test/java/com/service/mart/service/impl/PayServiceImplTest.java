package com.service.mart.service.impl;

import com.service.mart.dto.OrderDTO;
import com.service.mart.service.OrderService;
import com.service.mart.service.PayService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class PayServiceImplTest {
    @Autowired
    private PayService payService;
    @Autowired
    private OrderService orderService;

    @Test
    public void create() {
        OrderDTO orderDTO = orderService.findOne("157741164770925004");
        payService.create(orderDTO);
    }

    @Test
    public void refund(){
        OrderDTO orderDTO = orderService.findOne("157741164770925004");
        payService.refund(orderDTO);
    }
}