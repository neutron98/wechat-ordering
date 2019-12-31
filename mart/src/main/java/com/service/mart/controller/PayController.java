package com.service.mart.controller;

import com.lly835.bestpay.model.PayResponse;
import com.service.mart.dto.OrderDTO;
import com.service.mart.enums.ResultEnum;
import com.service.mart.exception.SellException;
import com.service.mart.service.OrderService;
import com.service.mart.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/pay")
public class PayController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl,
                               Map<String, Object> map){
        // 1. search the order
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        PayResponse payResponse = payService.create(orderDTO);
        map.put("orderId", "1111111");
        map.put("returnUrl", returnUrl);

        return new ModelAndView("pay/create", map);
    }


    /**
     * Asynchronous notification of payment result
     */
    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData){
        payService.notify(notifyData);

        //return the process result to WeChat
        return new ModelAndView("pay/success");
    }

}
