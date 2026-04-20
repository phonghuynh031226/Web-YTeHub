package com.poly.webytehub.service;

import com.poly.webytehub.dto.AdminProductRequest;
import com.poly.webytehub.entity.Category;
import com.poly.webytehub.entity.Product;
import com.poly.webytehub.repository.CategoryRepository;
import com.poly.webytehub.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class AdminProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public AdminProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> getProducts(String keyword, Integer categoryId) {
        String normalizedKeyword = keyword == null ? "" : keyword.trim().toLowerCase(Locale.ROOT);

        return productRepository.findAll().stream()
                .filter(product -> normalizedKeyword.isBlank()
                        || product.getProductName().toLowerCase(Locale.ROOT).contains(normalizedKeyword))
                .filter(product -> categoryId == null
                        || (product.getCategory() != null && categoryId.equals(product.getCategory().getCategoryID())))
                .sorted((a, b) -> {
                    LocalDateTime left = a.getCreatedDate() == null ? LocalDateTime.MIN : a.getCreatedDate();
                    LocalDateTime right = b.getCreatedDate() == null ? LocalDateTime.MIN : b.getCreatedDate();
                    return right.compareTo(left);
                })
                .toList();
    }

    public Map<String, Object> getStats() {
        List<Product> products = productRepository.findAll();
        long totalProducts = products.size();
        long lowStockProducts = products.stream().filter(product -> (product.getStock() == null ? 0 : product.getStock()) > 0 && product.getStock() <= 5).count();
        long outOfStockProducts = products.stream().filter(product -> (product.getStock() == null ? 0 : product.getStock()) <= 0).count();

        return Map.of(
                "totalProducts", totalProducts,
                "lowStockProducts", lowStockProducts,
                "outOfStockProducts", outOfStockProducts
        );
    }

    public Product create(AdminProductRequest request) {
        Product product = new Product();
        applyRequest(product, request, true);
        return productRepository.save(product);
    }

    public Product update(Integer id, AdminProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));
        applyRequest(product, request, false);
        return productRepository.save(product);
    }

    public void delete(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));
        productRepository.delete(product);
    }

    private void applyRequest(Product product, AdminProductRequest request, boolean isNew) {
        if (request.getCategoryId() == null) {
            throw new RuntimeException("Vui lòng chọn danh mục");
        }
        if (request.getProductName() == null || request.getProductName().isBlank()) {
            throw new RuntimeException("Tên sản phẩm không được để trống");
        }
        if (request.getPrice() == null || request.getPrice().signum() < 0) {
            throw new RuntimeException("Giá sản phẩm không hợp lệ");
        }
        if (request.getStock() == null || request.getStock() < 0) {
            throw new RuntimeException("Tồn kho không hợp lệ");
        }

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy danh mục"));

        product.setCategory(category);
        product.setProductName(request.getProductName().trim());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setImageURL(request.getImageURL() == null ? null : request.getImageURL().trim());
        product.setDescription(request.getDescription() == null ? null : request.getDescription().trim());
        if (isNew || product.getCreatedDate() == null) {
            product.setCreatedDate(LocalDateTime.now());
        }
    }
}
