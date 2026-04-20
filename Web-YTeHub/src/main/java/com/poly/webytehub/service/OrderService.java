package com.poly.webytehub.service;

import com.poly.webytehub.dto.OrderRequest;
import com.poly.webytehub.entity.*;
import com.poly.webytehub.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final CartRepository cartRepository;
    private final AddressRepository addressRepository;
    private final VoucherRepository voucherRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public OrderService(
            OrderRepository orderRepository,
            OrderDetailRepository orderDetailRepository,
            CartRepository cartRepository,
            AddressRepository addressRepository,
            VoucherRepository voucherRepository,
            UserRepository userRepository,
            ProductRepository productRepository
    ) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.cartRepository = cartRepository;
        this.addressRepository = addressRepository;
        this.voucherRepository = voucherRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public List<Order> getMyOrders(Integer userId) {
        return orderRepository.findByUserUserIDOrderByOrderDateDesc(userId);
    }

    @Transactional
    public Order createOrder(Integer userId, OrderRequest request) {
        User user = userRepository.findById(userId).orElseThrow();
        Address address = addressRepository.findById(request.getAddressId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy địa chỉ"));

        if (!address.getUser().getUserID().equals(userId)) {
            throw new RuntimeException("Địa chỉ không hợp lệ");
        }

        List<Cart> cartItems = cartRepository.findByUserUserID(userId);
        if (cartItems.isEmpty()) {
            throw new RuntimeException("Giỏ hàng đang trống");
        }

        BigDecimal subtotal = BigDecimal.ZERO;

        for (Cart item : cartItems) {
            Product product = item.getProduct();
            if (product.getStock() < item.getQuantity()) {
                throw new RuntimeException("Sản phẩm " + product.getProductName() + " không đủ tồn kho");
            }
            subtotal = subtotal.add(product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }

        Voucher voucher = null;
        BigDecimal discount = BigDecimal.ZERO;

        if (request.getVoucherCode() != null && !request.getVoucherCode().isBlank()) {
            voucher = voucherRepository.findByCodeIgnoreCase(request.getVoucherCode())
                    .orElseThrow(() -> new RuntimeException("Mã giảm giá không tồn tại"));

            if (voucher.getExpiredDate().isBefore(java.time.LocalDate.now())) {
                throw new RuntimeException("Mã giảm giá đã hết hạn");
            }

            if (totalBeforeDiscount(subtotal, request).compareTo(voucher.getMinOrderValue()) < 0) {
                throw new RuntimeException("Đơn hàng chưa đủ giá trị tối thiểu để dùng mã");
            }

            discount = voucher.getDiscountValue() == null ? BigDecimal.ZERO : voucher.getDiscountValue();
        }

        if (discount.compareTo(BigDecimal.ZERO) < 0) {
            discount = BigDecimal.ZERO;
        }

        BigDecimal shippingFee = normalizedShippingFee(request.getShippingFee());

        // DB đang có ràng buộc:
        //   FinalAmount = TotalAmount - DiscountAmount
        // và không có cột ShippingFee riêng.
        // Vì vậy TotalAmount phải bao gồm cả phí ship để không vi phạm constraint.
        BigDecimal totalAmount = subtotal.add(shippingFee);

        if (discount.compareTo(totalAmount) > 0) {
            discount = totalAmount;
        }

        BigDecimal finalAmount = totalAmount.subtract(discount);

        Order order = new Order();
        order.setUser(user);
        order.setAddress(address);
        order.setVoucher(voucher);
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(totalAmount);
        order.setDiscountAmount(discount);
        order.setFinalAmount(finalAmount);
        order.setPaymentMethod(request.getPaymentMethod());
        order.setOrderStatus("Pending");

        order = orderRepository.save(order);

        for (Cart item : cartItems) {
            Product product = item.getProduct();

            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setProduct(product);
            detail.setQuantity(item.getQuantity());
            detail.setUnitPrice(product.getPrice());
            detail.setTotalPrice(product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
            orderDetailRepository.save(detail);

            product.setStock(product.getStock() - item.getQuantity());
            productRepository.save(product);
        }

        cartRepository.deleteByUserUserID(userId);
        return order;
    }

    private BigDecimal normalizedShippingFee(BigDecimal shippingFee) {
        if (shippingFee == null || shippingFee.compareTo(BigDecimal.ZERO) < 0) {
            return BigDecimal.ZERO;
        }
        return shippingFee;
    }

    private BigDecimal totalBeforeDiscount(BigDecimal subtotal, OrderRequest request) {
        return subtotal.add(normalizedShippingFee(request.getShippingFee()));
    }
}
