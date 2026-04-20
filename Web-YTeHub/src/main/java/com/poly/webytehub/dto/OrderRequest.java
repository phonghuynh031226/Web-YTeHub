package com.poly.webytehub.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderRequest {
    private Integer addressId;
    private String paymentMethod;
    private String voucherCode;
    private String shippingMethod;
    private BigDecimal shippingFee;
}
