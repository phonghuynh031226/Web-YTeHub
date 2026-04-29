package com.poly.webytehub.service;

import com.poly.webytehub.entity.Product;
import com.poly.webytehub.repository.ProductRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getNewProducts() {
        return productRepository.findTop8ByCategoryActiveTrueOrderByCreatedDateDesc();
    }

    public List<Product> getFeaturedProducts() {
        List<Product> products = productRepository.findFeaturedProducts();
        return products.size() > 8 ? products.subList(0, 8) : products;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll().stream()
                .filter(product -> product.getCategory() == null || Boolean.TRUE.equals(product.getCategory().getActive()))
                .toList();
    }

    public Product getById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));
    }

    public Page<Product> searchProducts(
            String keyword,
            Integer categoryId,
            Integer minRating,
            Integer page,
            Integer size,
            String sort
    ) {
        Sort sorting = Sort.by("price");
        sorting = "desc".equalsIgnoreCase(sort) ? sorting.descending() : sorting.ascending();
        Pageable pageable = PageRequest.of(page, size, sorting);

        return productRepository.searchProducts(
                keyword == null || keyword.isBlank() ? null : keyword,
                categoryId,
                minRating,
                pageable
        );
    }
}