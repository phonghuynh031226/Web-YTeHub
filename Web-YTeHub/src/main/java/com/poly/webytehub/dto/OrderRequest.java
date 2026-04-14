package com.poly.webytehub.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    private Integer addressId;
    private String paymentMethod;
    private String voucherCode;
}