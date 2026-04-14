package com.poly.webytehub.controller;

import com.poly.webytehub.entity.Voucher;
import com.poly.webytehub.repository.VoucherRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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
}