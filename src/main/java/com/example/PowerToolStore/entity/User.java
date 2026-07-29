package com.example.PowerToolStore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="users")
public class User {
    @Setter(AccessLevel.NONE)
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @NotNull
    @Length(max=100)
    @Column(nullable = false, unique = true, length = 100)
    private String username;

    @NotNull
    @Column(nullable = false)
    private String hashedPassword;

    @Email
    @Column(unique = true)
    private String email;

    @Length(min = 10, max = 10)
    @Column(length = 10, unique = true)
    private String contactNumber;

    @CreationTimestamp
    @Setter(AccessLevel.NONE)
    @NotNull
//    @Column(nullable = false)
    private LocalDateTime createdAt;

    @NotNull
    @Length(max=100)
    @Column(nullable = false, length=100)
    private String role;

    @NotNull
    @Column(nullable = false)
    private Boolean isActive;

    protected User()
    {
        this.isActive = true;
    }
}
