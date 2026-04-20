package com.poly.webytehub.repository;

import com.poly.webytehub.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    List<OrderDetail> findByOrderOrderID(Integer orderId);
    boolean existsByOrderUserUserIDAndProductProductID(Integer userId, Integer productId);
    long countByOrderOrderID(Integer orderId);
}
