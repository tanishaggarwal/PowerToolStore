package com.example.PowerToolStore.dto.request.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
public class CategoryUpdateRequest {
    @Setter(AccessLevel.NONE)
    @NotNull
    private Long categoryId;

    @NotBlank
    private String categoryName;

    private String description;
}
