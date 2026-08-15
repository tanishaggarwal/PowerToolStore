package com.example.PowerToolStore.dto.request;

import com.example.PowerToolStore.constant.UserRole;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserRoleUpdateRequest {

    @NotNull
    private Long userId;

    @NotNull
    private UserRole role;
}
