package com.service.mart.service.impl;

import com.service.mart.entity.SellerInfo;
import com.service.mart.repository.SellerInfoRepository;
import com.service.mart.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    SellerInfoRepository repository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }
}
