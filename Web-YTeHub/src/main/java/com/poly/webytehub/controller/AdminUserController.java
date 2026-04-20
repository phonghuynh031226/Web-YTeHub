package com.poly.webytehub.controller;

import com.poly.webytehub.dto.AdminUserStatusRequest;
import com.poly.webytehub.entity.User;
import com.poly.webytehub.service.AdminUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/users")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class AdminUserController {

    private final AdminUserService adminUserService;

    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @GetMapping
    public List<Map<String, Object>> getUsers(@RequestParam(required = false) String keyword,
                                              @RequestParam(required = false) String status) {
        return adminUserService.getUsers(keyword, status);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Integer id, @RequestBody AdminUserStatusRequest request) {
        User user = adminUserService.updateStatus(id, request.getAccountStatus());
        return ResponseEntity.ok(Map.of(
                "message", "Cập nhật trạng thái tài khoản thành công",
                "userID", user.getUserID(),
                "accountStatus", user.getAccountStatus()
        ));
    }
}
