package com.poly.webytehub.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Vouchers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VoucherID")
    private Integer voucherID;

    @Column(name = "Code", nullable = false, unique = true, length = 50)
    private String code;

    @Column(name = "DiscountValue", nullable = false, precision = 18, scale = 2)
    private BigDecimal discountValue;

    @Column(name = "MinOrderValue", nullable = false, precision = 18, scale = 2)
    private BigDecimal minOrderValue;

    @Column(name = "ExpiredDate", nullable = false)
    private LocalDate expiredDate;

    @JsonIgnore
    @OneToMany(mappedBy = "voucher")
    private List<Order> orders;
}