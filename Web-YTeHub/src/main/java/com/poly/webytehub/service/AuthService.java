package com.poly.webytehub.service;

import com.poly.webytehub.dto.RegisterRequest;
import com.poly.webytehub.entity.User;
import com.poly.webytehub.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException("Email không tồn tại"));

        if (!"active".equalsIgnoreCase(user.getAccountStatus())) {
            throw new BadCredentialsException("Tài khoản đã bị khóa");
        }

        String dbPassword = user.getPassword();
        boolean matched;

        if (dbPassword != null && (dbPassword.startsWith("$2a$") || dbPassword.startsWith("$2b$") || dbPassword.startsWith("$2y$"))) {
            matched = passwordEncoder.matches(password, dbPassword);
        } else {
            matched = password.equals(dbPassword);
            if (matched) {
                user.setPassword(passwordEncoder.encode(password));
                userRepository.save(user);
            }
        }

        if (!matched) {
            throw new BadCredentialsException("Sai mật khẩu");
        }

        return user;
    }

    public User register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email đã tồn tại");
        }

        if (userRepository.existsByPhone(request.getPhone())) {
            throw new RuntimeException("Số điện thoại đã tồn tại");
        }

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("Mật khẩu xác nhận không khớp");
        }

        if (!Boolean.TRUE.equals(request.getAcceptTerms())) {
            throw new RuntimeException("Bạn phải đồng ý Điều khoản & Chính sách");
        }

        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("user");
        user.setAccountStatus("active");

        return userRepository.save(user);
    }
}