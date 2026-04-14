package com.poly.webytehub.repository;

import com.poly.webytehub.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VoucherRepository extends JpaRepository<Voucher, Integer> {
    List<Voucher> findByExpiredDateGreaterThanEqual(LocalDate today);
    Optional<Voucher> findByCodeIgnoreCase(String code);
}