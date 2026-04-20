package com.poly.webytehub.repository;

import com.poly.webytehub.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findTop8ByOrderByCreatedDateDesc();

    @Query("""
        SELECT od.product
        FROM OrderDetail od
        GROUP BY od.product
        ORDER BY SUM(od.quantity) DESC
    """)
    List<Product> findFeaturedProducts();
    @Query("""
SELECT p FROM Product p
LEFT JOIN Review r ON r.product = p
WHERE (:keyword IS NULL OR p.productName LIKE %:keyword%)
AND (:categoryId IS NULL OR p.category.categoryID = :categoryId)
GROUP BY p
HAVING (:minRating IS NULL OR AVG(r.rating) >= :minRating)
""")
    Page<Product> searchProducts(
            String keyword,
            Integer categoryId,
            Integer minRating,
            Pageable pageable
    );
}