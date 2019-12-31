//package com.service.mart.aspect;
//
//import com.service.mart.constant.CookieConstant;
//import com.service.mart.constant.RedisConstant;
//import com.service.mart.exception.SellerAuthorizeException;
//import com.service.mart.util.CookieUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//
//@Aspect
//@Component
//@Slf4j
//public class SellerAuthorizeAspect {
//    @Autowired
//    private StringRedisTemplate redisTemplate;
//
//    @Pointcut("execution(public * com.service.mart.controller.Seller*.*(..))" +
//    "&& !execution(public * com.service.mart.controller.SellerUserController*.*(..))") // login and logout
//    public void verify(){}
//
//    @Before("verify()")
//    public void doVerify(){
//        // get request
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        // search cookie
//
//        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
//        if (cookie == null){
//            log.warn("[Login Validation] Cannot find token in cookie");
//            throw new SellerAuthorizeException();
//        }
//
//        // search in redis
//        String tokenValue = redisTemplate.opsForValue()
//                .get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
//
//        if (StringUtils.isEmpty(tokenValue)){
//            log.warn("[Login Validation] Cannot find token in Redis");
//            throw new SellerAuthorizeException();
//        }
//    }
//
//}
