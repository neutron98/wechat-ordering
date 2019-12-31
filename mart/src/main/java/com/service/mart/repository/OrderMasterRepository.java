package com.service.mart.repository;

import com.service.mart.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
    /** browse a certain consumer's orders by page*/
    Page<OrderMaster> findByConsumerOpenid(String consumerOpenid, Pageable pageable);

}
