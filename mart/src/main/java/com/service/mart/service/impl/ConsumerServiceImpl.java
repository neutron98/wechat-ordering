package com.service.mart.service.impl;

import com.service.mart.dto.OrderDTO;
import com.service.mart.enums.ResultEnum;
import com.service.mart.exception.SellException;
import com.service.mart.service.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerServiceImpl implements ConsumerService {
    @Autowired
    private  OrderServiceImpl orderService;

    @Override
    public OrderDTO findOneOrder(String openid, String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null){
            return null;
        }
        if (!orderDTO.getConsumerOpenid().equalsIgnoreCase(openid)){
            log.error("[Search order]Openid does not match, openid={},orderDTO={}",
                    openid, orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }

    @Override
    public OrderDTO cancelOneOrder(String openid, String orderId) {
        OrderDTO orderDTO = findOneOrder(openid, orderId);
        if (orderDTO == null){ // notice that the way of dealing with null is different from findOneOrder
            log.error("[Cancel order]Can not find the order, orderId={}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        orderService.cancel(orderDTO);
        return null;
    }
}
