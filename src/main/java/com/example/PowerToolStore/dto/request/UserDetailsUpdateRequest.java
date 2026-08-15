package com.example.PowerToolStore.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class UserDetailsUpdateRequest {

    @NotNull
    private Long userId;

    @NotBlank
    @Length(max=100)
    private String username;

    @Email
    private String email;

    @Length(min = 10, max = 10)
    @NotBlank
    private String contactNumber;
}
