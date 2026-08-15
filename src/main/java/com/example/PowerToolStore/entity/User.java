package com.example.PowerToolStore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name="users")
public class User {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(nullable = false, unique = true, length = 100)
    private String username;

    @Column(nullable = false)
    private String hashedPassword;

    @Column(unique = true)
    private String email;

    @Column(length = 10, unique = true, nullable = false)
    private String contactNumber;

    @CreationTimestamp
    @Setter(AccessLevel.NONE)
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false, length=100)
    private String role;

    @Column(nullable = false)
    private Boolean isActive;

    protected User() {}
}
