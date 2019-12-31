package com.service.mart.controller;

import com.service.mart.config.ProjectUrlConfig;
import com.service.mart.constant.CookieConstant;
import com.service.mart.constant.RedisConstant;
import com.service.mart.entity.SellerInfo;
import com.service.mart.enums.ResultEnum;
import com.service.mart.service.SellerService;
import com.service.mart.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Sellers as user
 */
@Controller
@RequestMapping("/seller")
public class SellerUserController {
    @Autowired
    private SellerService sellerService;

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private ProjectUrlConfig projectUrlConfig;


    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid,
                              HttpServletResponse response,
                              Map<String, Object> map){
        // 1. match openid with data in database
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        if (sellerInfo == null){
            map.put("msg", ResultEnum.LOGIN_FAILED.getMsg());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        // 2. set token to redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        // key, value, time, unit
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), openid, expire, TimeUnit.SECONDS);
        // 3. set token to cookie
        CookieUtil.set(response, "token", token, expire);
        return new ModelAndView("redirect:" + projectUrlConfig.getSell() + "/seller/order/list");

    }


    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                               HttpServletResponse response,
                               Map<String, Object> map){
        // 1. search from the cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null){
            // 2. clear redis

            // 3. clear cookie
            redisTemplate.opsForValue().getOperations()
                    .delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        }

        map.put("msg", ResultEnum.LOGOUT_SUCCESS);
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/success",map);
    }
}
