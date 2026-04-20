package com.poly.webytehub.service;

import com.poly.webytehub.entity.Order;
import com.poly.webytehub.entity.OrderDetail;
import com.poly.webytehub.repository.OrderRepository;
import com.poly.webytehub.repository.ProductRepository;
import com.poly.webytehub.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public DashboardService(ProductRepository productRepository,
                            UserRepository userRepository,
                            OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    public Map<String, Object> getStats() {
        List<Order> allOrders = orderRepository.findAll();
        LocalDate now = LocalDate.now();
        BigDecimal monthlyRevenue = allOrders.stream()
                .filter(order -> order.getOrderDate() != null
                        && order.getOrderDate().getYear() == now.getYear()
                        && order.getOrderDate().getMonthValue() == now.getMonthValue())
                .map(order -> order.getFinalAmount() == null ? BigDecimal.ZERO : order.getFinalAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Map<String, Object> map = new HashMap<>();
        map.put("totalProducts", productRepository.count());
        map.put("totalCustomers", userRepository.countByRoleIgnoreCase("user"));
        map.put("monthlyRevenue", monthlyRevenue);
        return map;
    }

    public List<Map<String, Object>> getLatestOrders() {
        return orderRepository.findTop5ByOrderByOrderDateDesc().stream()
                .map(order -> {
                    Map<String, Object> item = new HashMap<>();
                    item.put("orderID", order.getOrderID());
                    item.put("status", order.getOrderStatus());
                    item.put("totalAmount", order.getFinalAmount());

                    List<OrderDetail> details = order.getOrderDetails();
                    if (details != null && !details.isEmpty() && details.get(0).getProduct() != null) {
                        item.put("productName", details.get(0).getProduct().getProductName());
                        item.put("imageURL", details.get(0).getProduct().getImageURL());
                    }
                    return item;
                })
                .toList();
    }

    public List<Map<String, Object>> getWeeklyRevenue() {
        LocalDate today = LocalDate.now();
        LocalDateTime start = today.minusDays(6).atStartOfDay();
        LocalDateTime end = today.plusDays(1).atStartOfDay();
        List<Order> orders = orderRepository.findByOrderDateBetween(start, end);

        List<Map<String, Object>> data = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");

        for (int i = 6; i >= 0; i--) {
            LocalDate date = today.minusDays(i);
            BigDecimal revenue = orders.stream()
                    .filter(order -> order.getOrderDate() != null && order.getOrderDate().toLocalDate().equals(date))
                    .map(order -> order.getFinalAmount() == null ? BigDecimal.ZERO : order.getFinalAmount())
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            data.add(Map.of(
                    "label", date.format(formatter),
                    "value", revenue
            ));
        }

        return data;
    }
}
