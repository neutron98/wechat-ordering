package com.service.mart.service;

import com.service.mart.dto.OrderDTO;

/**
 * Push Message
 */
public interface PushMessageService {
    /**
     * Order status update message
     * @param orderDTO
     */
    void orderStatus(OrderDTO orderDTO);

}
