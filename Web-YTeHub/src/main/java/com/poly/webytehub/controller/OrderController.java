package com.poly.webytehub.controller;

import com.poly.webytehub.dto.OrderRequest;
import com.poly.webytehub.entity.Order;
import com.poly.webytehub.entity.User;
import com.poly.webytehub.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    private User getCurrentUser(HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null) throw new RuntimeException("Chưa đăng nhập");
        return user;
    }

    @GetMapping
    public List<Order> getMyOrders(HttpSession session) {
        User user = getCurrentUser(session);
        return orderService.getMyOrders(user.getUserID());
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest request, HttpSession session) {
        User user = getCurrentUser(session);
        Order order = orderService.createOrder(user.getUserID(), request);
        return ResponseEntity.ok(Map.of(
                "message", "Đặt hàng thành công",
                "orderId", order.getOrderID()
        ));
    }
}