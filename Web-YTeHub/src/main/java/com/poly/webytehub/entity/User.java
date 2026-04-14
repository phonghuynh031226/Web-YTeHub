package com.poly.webytehub.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private Integer userID;

    @Column(name = "FullName", nullable = false, length = 100)
    private String fullName;

    @Column(name = "Email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "Phone", nullable = false, unique = true, length = 20)
    private String phone;

    @Column(name = "Password", nullable = false, length = 255)
    private String password;

    @Column(name = "Role", nullable = false, length = 20)
    private String role = "user";

    @Column(name = "AccountStatus", nullable = false, length = 20)
    private String accountStatus = "active";

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Address> addresses;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Cart> carts;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Review> reviews;
}