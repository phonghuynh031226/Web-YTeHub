package com.poly.webytehub.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductID")
    private Integer productID;

    @ManyToOne
    @JoinColumn(name = "CategoryID", nullable = false)
    private Category category;

    @Column(name = "ProductName", nullable = false, length = 150)
    private String productName;

    @Column(name = "Price", nullable = false, precision = 18, scale = 2)
    private BigDecimal price;

    @Column(name = "Stock", nullable = false)
    private Integer stock = 0;

    @Column(name = "ImageURL", length = 255)
    private String imageURL;

    @Column(name = "Description", length = 500)
    private String description;

    @Column(name = "CreatedDate", nullable = false)
    private LocalDateTime createdDate;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Cart> carts;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Review> reviews;

}