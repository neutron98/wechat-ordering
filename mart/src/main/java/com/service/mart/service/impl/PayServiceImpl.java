package com.service.mart.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.lly835.bestpay.utils.JsonUtil;
import com.service.mart.dto.OrderDTO;
import com.service.mart.enums.ResultEnum;
import com.service.mart.exception.SellException;
import com.service.mart.service.OrderService;
import com.service.mart.service.PayService;
import com.service.mart.util.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PayServiceImpl implements PayService {
    private static final String ORDER_NAME = "WeChat order";
    @Autowired
    private OrderService orderService;
    //@Autowired
    //private BestPayServiceImpl bestPayService;
    private BestPayServiceImpl bestPayService = new BestPayServiceImpl();

    @Override
    public PayResponse create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getConsumerOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("[WeChat pay] Begin payment, request={}", JsonUtil.toJson(payRequest));

        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("[WeChat pay] Begin, response={}", JsonUtil.toJson(payResponse));
        return payResponse;
    }

    @Override
    public PayResponse notify(String notifyData) {
        //1. verify signature
        //2. payment status
        //3. payment amount
        //4. payer(consumer who placed order == payer)

        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("[WeChat pay] Asynchronous notification, payResponse={}", JsonUtil.toJson(payResponse));

        //search the order
        OrderDTO orderDTO = orderService.findOne(payResponse.getOrderId());

        //check if exist
        if (orderDTO == null) {
            log.error("[WeChat pay] Asynchronous notification, order does not exist, orderId={}", payResponse.getOrderId());
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        //check whether the amount matches (0.10   0.1)
        if (!MathUtil.equals(payResponse.getOrderAmount(), orderDTO.getOrderAmount().doubleValue())) {
            log.error("[WeChat pay] Asynchronous notification, order amount does not match, " +
                            "orderId={}, amount from WeChat notification={}, amount from system={}",
                    payResponse.getOrderId(),
                    payResponse.getOrderAmount(),
                    orderDTO.getOrderAmount());
            throw new SellException(ResultEnum.WXPAY_NOTIFY_MONEY_VERIFY_ERROR);
        }

        //修改订单的支付状态
        orderService.pay(orderDTO);

        return payResponse;
    }

    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        RefundRequest request = new RefundRequest();
        request.setOrderId(orderDTO.getOrderId());
        request.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        request.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("[WeChat refund] request = {}", JsonUtil.toJson(request));
        RefundResponse refundResponse = bestPayService.refund(request);
        log.info("[WeChat refund] response = {}", JsonUtil.toJson(refundResponse));
        return refundResponse;
    }
}
