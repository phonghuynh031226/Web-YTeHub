package com.poly.webytehub.repository;

import com.poly.webytehub.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findByUserUserID(Integer userId);
    Optional<Cart> findByUserUserIDAndProductProductID(Integer userId, Integer productId);
    void deleteByUserUserID(Integer userId);

    @Modifying
    @Query("delete from Cart c where c.user.userID = :userId and c.cartID in :cartIds")
    void deleteSelectedByUser(@Param("userId") Integer userId, @Param("cartIds") List<Integer> cartIds);
}
