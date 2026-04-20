package com.poly.webytehub.service;

import com.poly.webytehub.entity.Order;
import com.poly.webytehub.repository.OrderDetailRepository;
import com.poly.webytehub.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class AdminOrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    public AdminOrderService(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    public List<Map<String, Object>> getOrders(String keyword, String status) {
        String normalizedKeyword = keyword == null ? "" : keyword.trim().toLowerCase(Locale.ROOT);
        String normalizedStatus = status == null ? "" : status.trim().toLowerCase(Locale.ROOT);

        return orderRepository.findAllByOrderByOrderDateDesc().stream()
                .filter(order -> normalizedKeyword.isBlank()
                        || String.valueOf(order.getOrderID()).contains(normalizedKeyword)
                        || (order.getUser() != null && order.getUser().getFullName() != null
                        && order.getUser().getFullName().toLowerCase(Locale.ROOT).contains(normalizedKeyword)))
                .filter(order -> normalizedStatus.isBlank()
                        || (order.getOrderStatus() != null && order.getOrderStatus().toLowerCase(Locale.ROOT).equals(normalizedStatus)))
                .map(order -> Map.<String, Object>of(
                        "orderID", order.getOrderID(),
                        "customerName", order.getUser() == null ? "" : order.getUser().getFullName(),
                        "customerEmail", order.getUser() == null ? "" : order.getUser().getEmail(),
                        "orderDate", order.getOrderDate(),
                        "paymentMethod", order.getPaymentMethod(),
                        "orderStatus", order.getOrderStatus(),
                        "totalAmount", order.getTotalAmount(),
                        "discountAmount", order.getDiscountAmount(),
                        "finalAmount", order.getFinalAmount(),
                        "itemCount", orderDetailRepository.countByOrderOrderID(order.getOrderID())
                ))
                .toList();
    }

    public Order updateStatus(Integer orderId, String status) {
        if (status == null || status.isBlank()) {
            throw new RuntimeException("Trạng thái đơn hàng không được để trống");
        }

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng"));
        order.setOrderStatus(status.trim());
        return orderRepository.save(order);
    }
}
