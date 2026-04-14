package com.poly.webytehub.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "Cart",
        uniqueConstraints = @UniqueConstraint(columnNames = {"UserID", "ProductID"})
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CartID")
    private Integer cartID;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "ProductID", nullable = false)
    private Product product;

    @Column(name = "Quantity", nullable = false)
    private Integer quantity = 1;
}