package com.service.mart.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.service.mart.dto.OrderDTO;
import com.service.mart.entity.OrderDetail;
import com.service.mart.enums.ResultEnum;
import com.service.mart.exception.SellException;
import com.service.mart.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class OrderForm2OrderDTOConverter {
    public static OrderDTO convert(OrderForm orderForm){
        Gson gson = new Gson();

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setFirstName(orderForm.getFirstName());
        orderDTO.setLastName(orderForm.getLastName());
        orderDTO.setConsumerAddress(orderForm.getAddress());
        orderDTO.setConsumerPhone(orderForm.getPhone());
        orderDTO.setConsumerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try{
            orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>(){}.getType());
        } catch (Exception e){
            log.error("[Object conversion]Error, String={}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
