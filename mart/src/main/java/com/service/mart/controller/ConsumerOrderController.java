package com.service.mart.controller;

import com.service.mart.converter.OrderForm2OrderDTOConverter;
import com.service.mart.dto.OrderDTO;
import com.service.mart.enums.ResultEnum;
import com.service.mart.exception.SellException;
import com.service.mart.form.OrderForm;
import com.service.mart.service.ConsumerService;
import com.service.mart.service.OrderService;
import com.service.mart.util.ResultVOUtil;
import com.service.mart.viewobject.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class ConsumerOrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ConsumerService consumerService;

    // create a new order
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            log.error("[Create order]Invalid parameters, orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage()); // record which field is invalid
        }
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);

        // empty cart
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("[Create order]Empty cart, orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO createResult = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());

        return ResultVOUtil.success(map);
    }
    // order list
    @GetMapping("/list")
    public ResultVO list(@RequestParam("openid") String openid,
                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                         @RequestParam(value = "size", defaultValue = "10") Integer size){
        if (StringUtils.isEmpty(openid)){
            log.error("[Search order list] openid is empty");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest request = new PageRequest(page, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid, request);

        return ResultVOUtil.success(orderDTOPage.getContent());
    }
    // browse one order (order detail)
    @GetMapping("/detail")
    public ResultVO detail(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId){

        OrderDTO orderDTO = consumerService.findOneOrder(openid, orderId);
        return ResultVOUtil.success(orderDTO);
    }
    // cancel order
    @PostMapping("/cancel")
    public ResultVO<OrderDTO> cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId){
        consumerService.cancelOneOrder(openid, orderId);
        return ResultVOUtil.success();
    }
}
