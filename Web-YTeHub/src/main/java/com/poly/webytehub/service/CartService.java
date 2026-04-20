package com.poly.webytehub.service;

import com.poly.webytehub.dto.CartRequest;
import com.poly.webytehub.entity.Cart;
import com.poly.webytehub.entity.Product;
import com.poly.webytehub.entity.User;
import com.poly.webytehub.repository.CartRepository;
import com.poly.webytehub.repository.ProductRepository;
import com.poly.webytehub.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public List<Cart> getMyCart(Integer userId) {
        return cartRepository.findByUserUserID(userId);
    }

    @Transactional
    public Cart addToCart(Integer userId, CartRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy user"));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));

        int qty = request.getQuantity() == null || request.getQuantity() <= 0 ? 1 : request.getQuantity();

        Cart cart = cartRepository.findByUserUserIDAndProductProductID(userId, request.getProductId())
                .orElseGet(() -> {
                    Cart c = new Cart();
                    c.setUser(user);
                    c.setProduct(product);
                    c.setQuantity(0);
                    return c;
                });

        cart.setQuantity(cart.getQuantity() + qty);
        return cartRepository.save(cart);
    }

    @Transactional
    public Cart updateQuantity(Integer userId, Integer cartId, Integer quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy giỏ hàng"));

        if (!cart.getUser().getUserID().equals(userId)) {
            throw new RuntimeException("Không có quyền");
        }

        if (quantity == null || quantity <= 0) {
            cartRepository.delete(cart);
            return null;
        }

        cart.setQuantity(quantity);
        return cartRepository.save(cart);
    }

    @Transactional
    public void deleteItem(Integer userId, Integer cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy giỏ hàng"));

        if (!cart.getUser().getUserID().equals(userId)) {
            throw new RuntimeException("Không có quyền");
        }

        cartRepository.delete(cart);
    }
}
