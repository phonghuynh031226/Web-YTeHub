package com.poly.webytehub.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AddressID")
    private Integer addressID;

    @Column(name = "ReceiverName", nullable = false, length = 100)
    private String receiverName;

    @Column(name = "ReceiverPhone", nullable = false, length = 20)
    private String receiverPhone;

    @Column(name = "Province", nullable = false, length = 100)
    private String province;

    @Column(name = "District", nullable = false, length = 100)
    private String district;

    @Column(name = "Ward", nullable = false, length = 100)
    private String ward;

    @Column(name = "AddressLine", nullable = false, length = 255)
    private String addressLine;

    @Column(name = "IsDefault", nullable = false)
    private Boolean isDefault = false;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;
}