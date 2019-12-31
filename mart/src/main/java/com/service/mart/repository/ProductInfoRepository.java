package com.service.mart.repository;

import com.service.mart.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {
    /** may need available products */
    List<ProductInfo> findByProductStatus(Integer productStatus);

}
