package com.poly.webytehub.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AdminProductRequest {
    private Integer categoryId;
    private String productName;
    private BigDecimal price;
    private Integer stock;
    private String imageURL;
    private String description;
}
