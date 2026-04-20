package com.poly.webytehub.controller;

import com.poly.webytehub.dto.ReviewRequest;
import com.poly.webytehub.entity.Review;
import com.poly.webytehub.entity.User;
import com.poly.webytehub.service.ReviewService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    private User getCurrentUser(HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Chưa đăng nhập");
        }
        return user;
    }

    @GetMapping("/product/{productId}")
    public List<Map<String, Object>> getProductReviews(@PathVariable Integer productId) {
        return reviewService.getProductReviews(productId);
    }

    @GetMapping("/can-review/{productId}")
    public ResponseEntity<?> canReview(@PathVariable Integer productId, HttpSession session) {
        User user = getCurrentUser(session);
        return ResponseEntity.ok(reviewService.canReview(user.getUserID(), productId));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ReviewRequest request, HttpSession session) {
        User user = getCurrentUser(session);
        Review review = reviewService.createReview(user.getUserID(), request);
        return ResponseEntity.ok(Map.of(
                "message", "Đánh giá thành công",
                "reviewID", review.getReviewID()
        ));
    }
}
