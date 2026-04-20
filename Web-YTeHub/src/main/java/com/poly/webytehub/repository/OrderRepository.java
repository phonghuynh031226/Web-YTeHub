package com.poly.webytehub.repository;

import com.poly.webytehub.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUserUserIDOrderByOrderDateDesc(Integer userId);
    List<Order> findTop5ByOrderByOrderDateDesc();
    List<Order> findAllByOrderByOrderDateDesc();
    List<Order> findByOrderDateBetween(LocalDateTime start, LocalDateTime end);
    long countByUserUserID(Integer userId);
}
