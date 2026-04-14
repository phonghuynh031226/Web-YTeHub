package com.poly.webytehub.repository;

import com.poly.webytehub.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByProductProductID(Integer productId);
}