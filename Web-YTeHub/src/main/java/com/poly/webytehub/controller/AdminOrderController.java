package com.poly.webytehub.controller;

import com.poly.webytehub.dto.AdminOrderStatusRequest;
import com.poly.webytehub.entity.Order;
import com.poly.webytehub.service.AdminOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/orders")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class AdminOrderController {

    private final AdminOrderService adminOrderService;

    public AdminOrderController(AdminOrderService adminOrderService) {
        this.adminOrderService = adminOrderService;
    }

    @GetMapping
    public List<Map<String, Object>> getOrders(@RequestParam(required = false) String keyword,
                                               @RequestParam(required = false) String status) {
        return adminOrderService.getOrders(keyword, status);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Integer id, @RequestBody AdminOrderStatusRequest request) {
        Order order = adminOrderService.updateStatus(id, request.getOrderStatus());
        return ResponseEntity.ok(Map.of(
                "message", "Cập nhật trạng thái đơn hàng thành công",
                "orderID", order.getOrderID(),
                "orderStatus", order.getOrderStatus()
        ));
    }
}
