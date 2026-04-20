package com.poly.webytehub.controller;

import com.poly.webytehub.dto.AdminProductRequest;
import com.poly.webytehub.entity.Product;
import com.poly.webytehub.service.AdminProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/products")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class AdminProductController {

    private final AdminProductService adminProductService;

    public AdminProductController(AdminProductService adminProductService) {
        this.adminProductService = adminProductService;
    }

    @GetMapping
    public List<Product> getProducts(@RequestParam(required = false) String keyword,
                                     @RequestParam(required = false) Integer categoryId) {
        return adminProductService.getProducts(keyword, categoryId);
    }

    @GetMapping("/stats")
    public Map<String, Object> getStats() {
        return adminProductService.getStats();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AdminProductRequest request) {
        Product product = adminProductService.create(request);
        return ResponseEntity.ok(Map.of(
                "message", "Thêm sản phẩm thành công",
                "product", product
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody AdminProductRequest request) {
        Product product = adminProductService.update(id, request);
        return ResponseEntity.ok(Map.of(
                "message", "Cập nhật sản phẩm thành công",
                "product", product
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        adminProductService.delete(id);
        return ResponseEntity.ok(Map.of("message", "Xóa sản phẩm thành công"));
    }
}
