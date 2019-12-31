package com.service.mart.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {

    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code){
        log.info("Entering auth() method...");
        log.info("code={}", code);

        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxabff71e7c82d5d86&redirect_uri=http://mart.natapp1.cc/sell/wechat/auth&response_type=code&scope=snsapi_base&state=STATE&connect_redirect=1#wechat_redirect";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        log.info("response={}", response);

    }
}
