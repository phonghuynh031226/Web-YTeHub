package com.poly.webytehub.service;

import com.poly.webytehub.dto.AdminCategoryRequest;
import com.poly.webytehub.entity.Category;
import com.poly.webytehub.repository.CategoryRepository;
import com.poly.webytehub.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminCategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public AdminCategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category create(AdminCategoryRequest request) {
        String name = normalizeName(request.getCategoryName());
        validateUniqueName(name, null);

        Category category = new Category();
        category.setCategoryName(name);
        category.setActive(request.getActive() == null ? true : request.getActive());
        return categoryRepository.save(category);
    }

    public Category update(Integer id, AdminCategoryRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy danh mục"));

        if (request.getCategoryName() != null) {
            String name = normalizeName(request.getCategoryName());
            validateUniqueName(name, id);
            category.setCategoryName(name);
        }
        if (request.getActive() != null) {
            category.setActive(request.getActive());
        }
        return categoryRepository.save(category);
    }

    public Category setActive(Integer id, boolean active) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy danh mục"));
        category.setActive(active);
        return categoryRepository.save(category);
    }

    public void delete(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy danh mục"));
        if (productRepository.countByCategoryCategoryID(id) > 0) {
            throw new RuntimeException("Danh mục đang có sản phẩm, hãy ẩn danh mục thay vì xóa");
        }
        categoryRepository.delete(category);
    }

    private String normalizeName(String categoryName) {
        String name = categoryName == null ? "" : categoryName.trim();
        if (name.isBlank()) {
            throw new RuntimeException("Tên danh mục không được để trống");
        }
        return name;
    }

    private void validateUniqueName(String name, Integer currentId) {
        boolean exists = categoryRepository.findAll().stream()
                .anyMatch(category -> category.getCategoryName() != null
                        && category.getCategoryName().trim().equalsIgnoreCase(name)
                        && (currentId == null || !category.getCategoryID().equals(currentId)));

        if (exists) {
            throw new RuntimeException("Danh mục đã tồn tại");
        }
    }
}
