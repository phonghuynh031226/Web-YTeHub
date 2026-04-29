package com.poly.webytehub.controller;

import com.poly.webytehub.entity.Voucher;
import com.poly.webytehub.repository.VoucherRepository;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vouchers")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class VoucherController {

    private final VoucherRepository voucherRepository;

    public VoucherController(VoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    @GetMapping
    public List<Voucher> getAvailable() {
        return voucherRepository.findByExpiredDateGreaterThanEqual(LocalDate.now());
    }

    @PostMapping("/validate")
    public Map<String, Object> validateVoucher(@RequestBody Map<String, Object> body) {
        String code = String.valueOf(body.getOrDefault("code", "")).trim();
        BigDecimal orderTotal = new BigDecimal(String.valueOf(body.getOrDefault("orderTotal", "0")));
        if (code.isBlank()) throw new RuntimeException("Vui lòng nhập mã giảm giá");

        Voucher voucher = voucherRepository.findByCodeIgnoreCase(code)
                .orElseThrow(() -> new RuntimeException("Mã giảm giá không tồn tại"));
        if (voucher.getExpiredDate().isBefore(LocalDate.now())) {
            throw new RuntimeException("Mã giảm giá đã hết hạn");
        }
        if (orderTotal.compareTo(voucher.getMinOrderValue()) < 0) {
            throw new RuntimeException("Đơn hàng chưa đủ giá trị tối thiểu để dùng mã");
        }
        BigDecimal discount = voucher.getDiscountValue() == null ? BigDecimal.ZERO : voucher.getDiscountValue();
        if (discount.compareTo(orderTotal) > 0) discount = orderTotal;
        return Map.of("voucherID", voucher.getVoucherID(), "code", voucher.getCode(), "discountValue", discount, "message", "Áp dụng mã thành công");
    }
}
