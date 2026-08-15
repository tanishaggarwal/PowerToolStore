package com.example.PowerToolStore.dto.response;

import com.example.PowerToolStore.constant.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class UserResponse {
    private Long userId;
    private String username;
    private String email;
    private String contactNumber;
    private LocalDateTime createdAt;
    private UserRole role;
}
