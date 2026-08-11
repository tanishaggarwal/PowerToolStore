package com.example.PowerToolStore.dto.response;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CategoryResponse {
    @NotNull
    private Long categoryId;

    @NotBlank
    private String categoryName;

    private String description;
}
