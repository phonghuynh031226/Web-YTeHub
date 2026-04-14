package com.poly.webytehub.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartRequest {
    private Integer productId;
    private Integer quantity;
}