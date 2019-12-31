package com.service.mart.service;

import com.service.mart.dto.CartDTO;
import com.service.mart.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    /** find one product using id*/
    ProductInfo findOne(String productId);

    /** find all in stock products */
    List<ProductInfo> findInAll();

    /** should display by page*/
    Page<ProductInfo> findAll(Pageable pageable);

    /** save or update*/
    ProductInfo save(ProductInfo productInfo);

    /** increase stock when customer cancelled an order*/
    void increaseStock(List<CartDTO> cartDTOList);

    /** decrease stock when customer place an order*/
    void decreaseStock(List<CartDTO> cartDTOList);

    /** upload */
    ProductInfo onMarket(String productId);

    /** discontinue */
    ProductInfo discontinue(String productId);
}
