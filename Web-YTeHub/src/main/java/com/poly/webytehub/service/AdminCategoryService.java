package com.poly.webytehub.service;

import com.poly.webytehub.dto.AdminCategoryRequest;
import com.poly.webytehub.entity.Category;
import com.poly.webytehub.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminCategoryService {

    private final CategoryRepository categoryRepository;

    public AdminCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category create(AdminCategoryRequest request) {
        String name = request.getCategoryName() == null ? "" : request.getCategoryName().trim();

        if (name.isBlank()) {
            throw new RuntimeException("Tên danh mục không được để trống");
        }

        boolean exists = categoryRepository.findAll().stream()
                .anyMatch(category -> category.getCategoryName() != null
                        && category.getCategoryName().trim().equalsIgnoreCase(name));

        if (exists) {
            throw new RuntimeException("Danh mục đã tồn tại");
        }

        Category category = new Category();
        category.setCategoryName(name);
        return categoryRepository.save(category);
    }
}
