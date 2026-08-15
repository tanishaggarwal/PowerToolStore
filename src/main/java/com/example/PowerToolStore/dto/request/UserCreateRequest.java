package com.example.PowerToolStore.dto.request;

import com.example.PowerToolStore.constant.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class UserCreateRequest {

    @NotBlank
    @Length(max=100)
    private String username;

    @NotBlank
    private String password;

    @Email
    private String email;

    @Length(min = 10, max = 10)
    @NotBlank
    private String contactNumber;

    @NotNull
    private UserRole role;

    @NotNull
    private Boolean isActive;
}
