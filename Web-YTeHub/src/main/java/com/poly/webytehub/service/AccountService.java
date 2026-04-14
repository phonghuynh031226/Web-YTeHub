package com.poly.webytehub.service;

import com.poly.webytehub.dto.ChangePasswordRequest;
import com.poly.webytehub.dto.UpdateProfileRequest;
import com.poly.webytehub.entity.User;
import com.poly.webytehub.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUser(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
    }

    public User updateProfile(Integer userId, UpdateProfileRequest request) {
        User user = getUser(userId);

        user.setFullName(request.getFullName());
        user.setPhone(request.getPhone());

        return userRepository.save(user);
    }

    public void changePassword(Integer userId, ChangePasswordRequest request) {
        User user = getUser(userId);

        boolean matched;
        String dbPassword = user.getPassword();

        if (dbPassword != null && (dbPassword.startsWith("$2a$") || dbPassword.startsWith("$2b$") || dbPassword.startsWith("$2y$"))) {
            matched = passwordEncoder.matches(request.getCurrentPassword(), dbPassword);
        } else {
            matched = request.getCurrentPassword().equals(dbPassword);
        }

        if (!matched) {
            throw new RuntimeException("Mật khẩu hiện tại không đúng");
        }

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("Xác nhận mật khẩu không khớp");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }
}