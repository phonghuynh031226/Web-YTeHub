package com.poly.webytehub.controller;

import com.poly.webytehub.entity.Address;
import com.poly.webytehub.entity.User;
import com.poly.webytehub.repository.AddressRepository;
import com.poly.webytehub.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/addresses")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class AddressController {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public AddressController(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    private User getCurrentUser(HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null) throw new RuntimeException("Chưa đăng nhập");
        return userRepository.findById(user.getUserID()).orElseThrow();
    }

    @GetMapping
    public List<Address> getMyAddresses(HttpSession session) {
        User user = getCurrentUser(session);
        return addressRepository.findByUserUserIDOrderByIsDefaultDescAddressIDDesc(user.getUserID());
    }

    @PostMapping
    public Address create(@RequestBody Address address, HttpSession session) {
        User user = getCurrentUser(session);
        address.setAddressID(null);
        address.setUser(user);

        if (Boolean.TRUE.equals(address.getIsDefault())) {
            List<Address> old = addressRepository.findByUserUserIDOrderByIsDefaultDescAddressIDDesc(user.getUserID());
            for (Address item : old) {
                item.setIsDefault(false);
            }
            addressRepository.saveAll(old);
        }

        return addressRepository.save(address);
    }

    @PutMapping("/{id}")
    public Address update(@PathVariable Integer id, @RequestBody Address request, HttpSession session) {
        User user = getCurrentUser(session);

        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy địa chỉ"));

        if (!address.getUser().getUserID().equals(user.getUserID())) {
            throw new RuntimeException("Không có quyền");
        }

        address.setReceiverName(request.getReceiverName());
        address.setReceiverPhone(request.getReceiverPhone());
        address.setProvince(request.getProvince());
        address.setDistrict(request.getDistrict());
        address.setWard(request.getWard());
        address.setAddressLine(request.getAddressLine());
        address.setIsDefault(request.getIsDefault());

        if (Boolean.TRUE.equals(request.getIsDefault())) {
            List<Address> old = addressRepository.findByUserUserIDOrderByIsDefaultDescAddressIDDesc(user.getUserID());
            for (Address item : old) {
                if (!item.getAddressID().equals(address.getAddressID())) {
                    item.setIsDefault(false);
                }
            }
            addressRepository.saveAll(old);
        }

        return addressRepository.save(address);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id, HttpSession session) {
        User user = getCurrentUser(session);

        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy địa chỉ"));

        if (!address.getUser().getUserID().equals(user.getUserID())) {
            throw new RuntimeException("Không có quyền");
        }

        addressRepository.delete(address);
        return ResponseEntity.ok(Map.of("message", "Xóa địa chỉ thành công"));
    }
}