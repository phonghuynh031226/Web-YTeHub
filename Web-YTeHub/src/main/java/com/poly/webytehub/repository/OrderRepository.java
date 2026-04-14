package com.poly.webytehub.repository;

import com.poly.webytehub.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUserUserIDOrderByOrderDateDesc(Integer userId);
}