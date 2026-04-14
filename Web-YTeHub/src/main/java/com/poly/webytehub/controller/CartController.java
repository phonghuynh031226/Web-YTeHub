package com.poly.webytehub.controller;

import com.poly.webytehub.dto.CartRequest;
import com.poly.webytehub.entity.Cart;
import com.poly.webytehub.entity.User;
import com.poly.webytehub.service.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    private User getCurrentUser(HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null) throw new RuntimeException("Chưa đăng nhập");
        return user;
    }

    @GetMapping
    public List<Cart> getCart(HttpSession session) {
        User user = getCurrentUser(session);
        return cartService.getMyCart(user.getUserID());
    }

    @PostMapping
    public Cart add(@RequestBody CartRequest request, HttpSession session) {
        User user = getCurrentUser(session);
        return cartService.addToCart(user.getUserID(), request);
    }

    @PutMapping("/{cartId}")
    public ResponseEntity<?> update(@PathVariable Integer cartId, @RequestBody Map<String, Integer> body, HttpSession session) {
        User user = getCurrentUser(session);
        Cart cart = cartService.updateQuantity(user.getUserID(), cartId, body.get("quantity"));
        if (cart == null) {
            return ResponseEntity.ok(Map.of("message", "Đã xóa sản phẩm khỏi giỏ"));
        }
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<?> delete(@PathVariable Integer cartId, HttpSession session) {
        User user = getCurrentUser(session);
        cartService.deleteItem(user.getUserID(), cartId);
        return ResponseEntity.ok(Map.of("message", "Đã xóa sản phẩm"));
    }
}