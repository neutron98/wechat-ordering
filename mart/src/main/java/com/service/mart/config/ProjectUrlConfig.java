package com.service.mart.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "projectUrl")
@Component
public class ProjectUrlConfig {
    /**
     * Wechat public platform authorize url.
     */
    public String wechatMpAuthorize;
    /**
     * Wechat open platform authorize url.
     */
    public String wechatOpenAuthorize;

    /**
     * Selling system url.
     */
    public String sell;
}
