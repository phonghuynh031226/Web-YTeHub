package com.poly.webytehub.repository;

import com.poly.webytehub.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findByUserUserID(Integer userId);
    Optional<Cart> findByUserUserIDAndProductProductID(Integer userId, Integer productId);
    void deleteByUserUserID(Integer userId);
}