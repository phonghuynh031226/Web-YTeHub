package com.poly.webytehub.controller;

import com.poly.webytehub.entity.Voucher;
import com.poly.webytehub.repository.VoucherRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/vouchers")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class AdminVoucherController {
    private final VoucherRepository voucherRepository;

    public AdminVoucherController(VoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    @GetMapping
    public List<Voucher> getAll() {
        return voucherRepository.findAll();
    }

    @PostMapping
    public Voucher create(@RequestBody Voucher voucher) {
        voucher.setVoucherID(null);
        voucher.setCode(voucher.getCode().trim().toUpperCase());
        return voucherRepository.save(voucher);
    }

    @PutMapping("/{id}")
    public Voucher update(@PathVariable Integer id, @RequestBody Voucher body) {
        Voucher voucher = voucherRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy mã giảm giá"));
        voucher.setCode(body.getCode().trim().toUpperCase());
        voucher.setDiscountValue(body.getDiscountValue());
        voucher.setMinOrderValue(body.getMinOrderValue());
        voucher.setExpiredDate(body.getExpiredDate());
        return voucherRepository.save(voucher);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        voucherRepository.deleteById(id);
    }
}
