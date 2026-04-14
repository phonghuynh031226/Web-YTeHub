package com.poly.webytehub.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderID")
    private Integer orderID;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "AddressID", nullable = false)
    private Address address;

    @ManyToOne
    @JoinColumn(name = "VoucherID")
    private Voucher voucher;

    @Column(name = "OrderDate", nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "TotalAmount", nullable = false, precision = 18, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "DiscountAmount", nullable = false, precision = 18, scale = 2)
    private BigDecimal discountAmount = BigDecimal.ZERO;

    @Column(name = "FinalAmount", nullable = false, precision = 18, scale = 2)
    private BigDecimal finalAmount;

    @Column(name = "PaymentMethod", nullable = false, length = 50)
    private String paymentMethod;

    @Column(name = "OrderStatus", nullable = false, length = 50)
    private String orderStatus;

    @JsonIgnore
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;
}