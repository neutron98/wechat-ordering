package com.service.mart.service;

import com.service.mart.entity.SellerInfo;

public interface SellerService {
    /**
     * Find seller info by open id
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);
}
