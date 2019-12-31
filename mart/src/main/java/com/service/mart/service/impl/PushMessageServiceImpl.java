package com.service.mart.service.impl;

import com.service.mart.config.WechatAccountConfig;
import com.service.mart.dto.OrderDTO;
import com.service.mart.service.PushMessageService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class PushMessageServiceImpl implements PushMessageService {

    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private WechatAccountConfig accountConfig;

    @Override
    public void orderStatus(OrderDTO orderDTO) {
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setTemplateId(accountConfig.getTemplateId().get("orderStatus"));
        //templateMessage.setToUser("oCJ0SxH2F52koCtf1XSHLaFBEUW4");
        templateMessage.setToUser(orderDTO.getConsumerOpenid());
        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first", "Your order is on the way."),
                new WxMpTemplateData("keyword1", "Sichuan Gourmet"),
                new WxMpTemplateData("keyword2", "17361013480"),
                new WxMpTemplateData("keyword3", orderDTO.getOrderId()),
                new WxMpTemplateData("keyword4", orderDTO.getOrderStatusEnum().getMessage()),
                new WxMpTemplateData("keyword5", "$" + orderDTO.getOrderAmount()),
                new WxMpTemplateData("remark", "Thank you for your order")
        );
        templateMessage.setData(data);
        try{
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e){
            log.error("{WeChat Template Message] Sending failed: {} ", e);
        }
    }
}
