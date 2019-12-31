package com.service.mart.service;

import com.service.mart.dto.OrderDTO;

public interface ConsumerService {
    // search an order
    OrderDTO findOneOrder(String openid, String orderId);

    // cancel an order
    OrderDTO cancelOneOrder(String openid, String orderId);
}
