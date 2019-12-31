package com.service.mart.service.impl;

import com.service.mart.dto.CartDTO;
import com.service.mart.entity.ProductInfo;
import com.service.mart.enums.ProductStatusEnum;
import com.service.mart.enums.ResultEnum;
import com.service.mart.exception.SellException;
import com.service.mart.repository.ProductInfoRepository;
import com.service.mart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@CacheConfig(cacheNames = "product")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository repository;

    @Override
    @Cacheable(key = "123")
    public ProductInfo findOne(String productId) {
        return repository.findOne(productId);
    }

    @Override
    public List<ProductInfo> findInAll() {
        return repository.findByProductStatus(ProductStatusEnum.ON.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @CachePut(key = "123")
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO: cartDTOList){
            ProductInfo productInfo = repository.findOne(cartDTO.getProductId());
            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO: cartDTOList){
            ProductInfo productInfo = repository.findOne(cartDTO.getProductId());
            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }

    @Override
    public ProductInfo onMarket(String productId) {
        ProductInfo productInfo = repository.findOne(productId);
        //validate the productInfo object
        if (productInfo == null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        //validate the status
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.ON){
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        //update
        productInfo.setProductStatus(ProductStatusEnum.ON.getCode());
        return repository.save(productInfo);
    }

    @Override
    public ProductInfo discontinue(String productId) {
        ProductInfo productInfo = repository.findOne(productId);
        //validate the productInfo object
        if (productInfo == null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        //validate the status
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.DIS){
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        //update
        productInfo.setProductStatus(ProductStatusEnum.DIS.getCode());
        return repository.save(productInfo);
    }
}
