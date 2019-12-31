package com.service.mart.service;

import com.service.mart.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Order service for consumers and sellers
 */
public interface OrderService {
    /***
     * Service for consumers
     */

    /** create a new order */
    OrderDTO create(OrderDTO orderDTO);

    /** search an order */
    OrderDTO findOne(String orderId);

    /** browse the order list by page, for consumers */
    Page<OrderDTO> findList(String consumerOpenid, Pageable pageable);

    /** cancel the order */
    OrderDTO cancel(OrderDTO orderDTO);

    /** pay for the order */
    OrderDTO pay(OrderDTO orderDTO);

    /** finish the order */
    OrderDTO finish(OrderDTO orderDTO);

    /***
     * Service for sellers
     */

    /** browse the order list by page */
    Page<OrderDTO> findList(Pageable pageable);
}
