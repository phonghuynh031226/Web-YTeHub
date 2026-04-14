package com.poly.webytehub.controller;

import com.poly.webytehub.dto.ChangePasswordRequest;
import com.poly.webytehub.dto.UpdateProfileRequest;
import com.poly.webytehub.entity.User;
import com.poly.webytehub.service.AccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/account")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    private User getCurrentUser(HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null) throw new RuntimeException("Chưa đăng nhập");
        return user;
    }

    @GetMapping("/profile")
    public ResponseEntity<?> profile(HttpSession session) {
        User current = getCurrentUser(session);
        User user = accountService.getUser(current.getUserID());
        session.setAttribute("currentUser", user);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(@RequestBody UpdateProfileRequest request, HttpSession session) {
        User current = getCurrentUser(session);
        User updated = accountService.updateProfile(current.getUserID(), request);
        session.setAttribute("currentUser", updated);
        return ResponseEntity.ok(Map.of("message", "Cập nhật hồ sơ thành công", "user", updated));
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request, HttpSession session) {
        User current = getCurrentUser(session);
        accountService.changePassword(current.getUserID(), request);
        return ResponseEntity.ok(Map.of("message", "Đổi mật khẩu thành công"));
    }
}