package com.service.mart.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {
    /**
     * Public Platform id
     */
    private String mpAppId;
    /**
     * Public Platform secret
     */
    private String mpAppSecret;
    /**
     * Open Paltform id
     */
    private String openAppId;
    /**
     * Open Platform secret
     */
    private String openAppSecret;
    /**
     * Merchant Id
     */
    private String mchId;

    /**
     * Merchant key
     */
    private String mchKey;

    /**
     * Merchant key path
     */
    private String keyPath;
    /**
     * WeChat pay notify Url
     */
    private String notifyUrl;

    /**
     * WeChat Template Id
     */
    private Map<String, String> templateId;
}
