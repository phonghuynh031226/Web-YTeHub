package com.poly.webytehub.controller;

import com.poly.webytehub.dto.LoginRequest;
import com.poly.webytehub.dto.RegisterRequest;
import com.poly.webytehub.entity.User;
import com.poly.webytehub.service.AuthService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        try {
            User user = authService.register(request);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Đăng ký thành công");
            response.put("userID", user.getUserID());
            response.put("email", user.getEmail());
            response.put("fullName", user.getFullName());
            response.put("role", user.getRole());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpSession session) {
        try {
            User user = authService.login(request.getEmail(), request.getPassword());

            var authorities = List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().toUpperCase()));
            var authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), null, authorities);

            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);

            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);
            session.setAttribute("currentUser", user);

            return ResponseEntity.ok(Map.of(
                    "message", "Đăng nhập thành công",
                    "userID", user.getUserID(),
                    "email", user.getEmail(),
                    "fullName", user.getFullName(),
                    "role", user.getRole()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            return ResponseEntity.status(401).body(Map.of("message", "Chưa đăng nhập"));
        }

        return ResponseEntity.ok(Map.of(
                "userID", user.getUserID(),
                "email", user.getEmail(),
                "fullName", user.getFullName(),
                "phone", user.getPhone(),
                "role", user.getRole()
        ));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        SecurityContextHolder.clearContext();
        session.invalidate();
        return ResponseEntity.ok(Map.of("message", "Đăng xuất thành công"));
    }
}