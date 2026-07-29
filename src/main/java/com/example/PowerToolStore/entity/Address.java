package com.example.PowerToolStore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.SettingDefinition;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="addresses")
public class Address {
    @Setter(AccessLevel.NONE)
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addressId;

    @NotNull
//    @Column(unique = true, nullable = false)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false, unique = true)
    //private Long userId;
    private User user;

    @NotNull
    @Column(nullable = false)
    private int pincode;

    @NotNull
    @Column(nullable = false)
    private String city;

    @NotNull
    @Column(nullable = false)
    private String state;

    @NotNull
    @Column(nullable = false)
    private String country;

    @NotNull
    @Column(nullable = false)
    private String houseNumber;

    @NotNull
    @Column(nullable = false)
    private String address;

    @Setter(AccessLevel.NONE)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    protected Address(){}
}
