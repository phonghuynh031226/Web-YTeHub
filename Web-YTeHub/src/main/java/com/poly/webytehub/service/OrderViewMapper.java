package com.poly.webytehub.service;

import com.poly.webytehub.entity.Address;
import com.poly.webytehub.entity.Order;
import com.poly.webytehub.entity.OrderDetail;
import com.poly.webytehub.entity.Product;

import java.util.List;
import java.util.Map;

public final class OrderViewMapper {
    private OrderViewMapper() {
    }

    public static Map<String, Object> toOrderMap(Order order, List<OrderDetail> details) {
        Address address = order.getAddress();
        return Map.ofEntries(
                Map.entry("orderID", order.getOrderID()),
                Map.entry("customerName", order.getUser() == null ? "" : safe(order.getUser().getFullName())),
                Map.entry("customerEmail", order.getUser() == null ? "" : safe(order.getUser().getEmail())),
                Map.entry("orderDate", order.getOrderDate()),
                Map.entry("paymentMethod", safe(order.getPaymentMethod())),
                Map.entry("orderStatus", safe(order.getOrderStatus())),
                Map.entry("totalAmount", order.getTotalAmount()),
                Map.entry("discountAmount", order.getDiscountAmount()),
                Map.entry("finalAmount", order.getFinalAmount()),
                Map.entry("itemCount", details == null ? 0 : details.stream().mapToInt(item -> item.getQuantity() == null ? 0 : item.getQuantity()).sum()),
                Map.entry("address", address == null ? Map.of() : Map.ofEntries(
                        Map.entry("receiverName", safe(address.getReceiverName())),
                        Map.entry("receiverPhone", safe(address.getReceiverPhone())),
                        Map.entry("province", safe(address.getProvince())),
                        Map.entry("district", safe(address.getDistrict())),
                        Map.entry("ward", safe(address.getWard())),
                        Map.entry("addressLine", safe(address.getAddressLine()))
                )),
                Map.entry("details", details == null ? List.of() : details.stream().map(OrderViewMapper::toDetailMap).toList())
        );
    }

    private static Map<String, Object> toDetailMap(OrderDetail detail) {
        Product product = detail.getProduct();
        return Map.ofEntries(
                Map.entry("orderDetailID", detail.getOrderDetailID()),
                Map.entry("productID", product == null ? 0 : product.getProductID()),
                Map.entry("productName", product == null ? "" : safe(product.getProductName())),
                Map.entry("imageURL", product == null ? "" : safe(product.getImageURL())),
                Map.entry("quantity", detail.getQuantity()),
                Map.entry("unitPrice", detail.getUnitPrice()),
                Map.entry("totalPrice", detail.getTotalPrice())
        );
    }

    private static String safe(String value) {
        return value == null ? "" : value;
    }
}
