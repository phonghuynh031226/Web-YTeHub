package com.poly.webytehub.service;

import com.poly.webytehub.entity.User;
import com.poly.webytehub.repository.OrderRepository;
import com.poly.webytehub.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class AdminUserService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public AdminUserService(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    public List<Map<String, Object>> getUsers(String keyword, String status) {
        String normalizedKeyword = keyword == null ? "" : keyword.trim().toLowerCase(Locale.ROOT);
        String normalizedStatus = status == null ? "" : status.trim().toLowerCase(Locale.ROOT);

        return userRepository.findAll().stream()
                .filter(user -> !"admin".equalsIgnoreCase(user.getRole()))
                .filter(user -> normalizedKeyword.isBlank()
                        || user.getFullName().toLowerCase(Locale.ROOT).contains(normalizedKeyword)
                        || user.getEmail().toLowerCase(Locale.ROOT).contains(normalizedKeyword)
                        || user.getPhone().toLowerCase(Locale.ROOT).contains(normalizedKeyword))
                .filter(user -> normalizedStatus.isBlank()
                        || (user.getAccountStatus() != null && user.getAccountStatus().toLowerCase(Locale.ROOT).equals(normalizedStatus)))
                .map(user -> Map.<String, Object>of(
                        "userID", user.getUserID(),
                        "fullName", user.getFullName(),
                        "email", user.getEmail(),
                        "phone", user.getPhone(),
                        "role", user.getRole(),
                        "accountStatus", user.getAccountStatus(),
                        "orderCount", orderRepository.countByUserUserID(user.getUserID())
                ))
                .toList();
    }

    public User updateStatus(Integer userId, String status) {
        if (status == null || status.isBlank()) {
            throw new RuntimeException("Trạng thái tài khoản không được để trống");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

        user.setAccountStatus(status.trim().toLowerCase(Locale.ROOT));
        return userRepository.save(user);
    }
}
