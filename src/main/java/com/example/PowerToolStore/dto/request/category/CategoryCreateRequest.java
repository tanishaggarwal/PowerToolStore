package com.example.PowerToolStore.dto.request.category;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CategoryCreateRequest {
    @NotBlank
    private String categoryName;

    private String description;
}
