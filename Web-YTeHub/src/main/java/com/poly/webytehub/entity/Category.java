package com.poly.webytehub.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryID")
    private Integer categoryID;

    @Column(name = "CategoryName", nullable = false, length = 100)
    private String categoryName;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}