package com.poly.webytehub.service;

import com.poly.webytehub.dto.ReviewRequest;
import com.poly.webytehub.entity.Product;
import com.poly.webytehub.entity.Review;
import com.poly.webytehub.entity.User;
import com.poly.webytehub.repository.OrderDetailRepository;
import com.poly.webytehub.repository.ProductRepository;
import com.poly.webytehub.repository.ReviewRepository;
import com.poly.webytehub.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderDetailRepository orderDetailRepository;

    public ReviewService(ReviewRepository reviewRepository,
                         ProductRepository productRepository,
                         UserRepository userRepository,
                         OrderDetailRepository orderDetailRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    public List<Map<String, Object>> getProductReviews(Integer productId) {
        return reviewRepository.findByProductProductIDOrderByReviewIDDesc(productId)
                .stream()
                .map(review -> Map.<String, Object>of(
                        "reviewID", review.getReviewID(),
                        "rating", review.getRating(),
                        "comment", review.getComment() == null ? "" : review.getComment(),
                        "userID", review.getUser().getUserID(),
                        "userName", review.getUser().getFullName()
                ))
                .toList();
    }

    public boolean canReview(Integer userId, Integer productId) {
        boolean boughtProduct = orderDetailRepository.existsByOrderUserUserIDAndProductProductID(userId, productId);
        boolean reviewed = reviewRepository.existsByUserUserIDAndProductProductID(userId, productId);
        return boughtProduct && !reviewed;
    }

    public Review createReview(Integer userId, ReviewRequest request) {
        if (request.getProductId() == null) {
            throw new RuntimeException("Thiếu sản phẩm để đánh giá");
        }

        Integer rating = request.getRating();
        if (rating == null || rating < 1 || rating > 5) {
            throw new RuntimeException("Số sao phải từ 1 đến 5");
        }

        String comment = request.getComment() == null ? "" : request.getComment().trim();
        if (comment.isEmpty()) {
            throw new RuntimeException("Vui lòng nhập nội dung đánh giá");
        }

        if (!canReview(userId, request.getProductId())) {
            throw new RuntimeException("Bạn chỉ có thể đánh giá sau khi đã mua sản phẩm và chưa đánh giá trước đó");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));

        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setRating(rating);
        review.setComment(comment);

        return reviewRepository.save(review);
    }
}
