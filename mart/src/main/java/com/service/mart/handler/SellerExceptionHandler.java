package com.service.mart.handler;

import com.service.mart.config.ProjectUrlConfig;
import com.service.mart.exception.SellException;
import com.service.mart.exception.SellerAuthorizeException;
import com.service.mart.util.ResultVOUtil;
import com.service.mart.viewobject.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SellerExceptionHandler {
    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    // login exception
    //http://mart.natapp1.cc/sell/wechat/qrAuthorize?returnUrl=http://mart.natapp1.cc/sell/seller/login
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handleAuthorizeException(){
        return new ModelAndView("redirect:"
                .concat(projectUrlConfig.getWechatOpenAuthorize())
                .concat("/sell/wechat/qrAuthorize")
                .concat("?returnUrl=")
                .concat(projectUrlConfig.getSell())
                .concat("/sell/seller/login"));
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handleSellerException(SellException e){
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }

}
