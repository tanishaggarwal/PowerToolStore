package com.example.PowerToolStore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.SettingDefinition;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@Table(name="addresses")
public class Address {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addressId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false, unique = true)
    private User user;

    @Column(nullable = false)
    private String pincode;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String houseNumber;

    @Column(nullable = false)
    private String address;

    @Setter(AccessLevel.NONE)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    protected Address(){}
}
