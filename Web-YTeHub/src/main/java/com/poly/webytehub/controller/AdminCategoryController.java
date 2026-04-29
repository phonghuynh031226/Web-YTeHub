package com.poly.webytehub.controller;

import com.poly.webytehub.dto.AdminCategoryRequest;
import com.poly.webytehub.entity.Category;
import com.poly.webytehub.service.AdminCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/categories")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class AdminCategoryController {

    private final AdminCategoryService adminCategoryService;

    public AdminCategoryController(AdminCategoryService adminCategoryService) {
        this.adminCategoryService = adminCategoryService;
    }

    @GetMapping
    public List<Category> getAll() {
        return adminCategoryService.getAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AdminCategoryRequest request) {
        Category category = adminCategoryService.create(request);
        return ResponseEntity.ok(Map.of(
                "message", "Tạo danh mục thành công",
                "category", category
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody AdminCategoryRequest request) {
        Category category = adminCategoryService.update(id, request);
        return ResponseEntity.ok(Map.of(
                "message", "Cập nhật danh mục thành công",
                "category", category
        ));
    }

    @PutMapping("/{id}/active")
    public ResponseEntity<?> setActive(@PathVariable Integer id, @RequestBody AdminCategoryRequest request) {
        Category category = adminCategoryService.setActive(id, request.getActive() == null || request.getActive());
        return ResponseEntity.ok(Map.of(
                "message", category.getActive() ? "Đã hiện danh mục" : "Đã ẩn danh mục",
                "category", category
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        adminCategoryService.delete(id);
        return ResponseEntity.ok(Map.of("message", "Xóa danh mục thành công"));
    }
}
