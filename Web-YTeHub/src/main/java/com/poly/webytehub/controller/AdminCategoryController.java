package com.poly.webytehub.controller;

import com.poly.webytehub.dto.AdminCategoryRequest;
import com.poly.webytehub.entity.Category;
import com.poly.webytehub.service.AdminCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin/categories")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class AdminCategoryController {

    private final AdminCategoryService adminCategoryService;

    public AdminCategoryController(AdminCategoryService adminCategoryService) {
        this.adminCategoryService = adminCategoryService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AdminCategoryRequest request) {
        Category category = adminCategoryService.create(request);
        return ResponseEntity.ok(Map.of(
                "message", "Tạo danh mục thành công",
                "category", category
        ));
    }
}
