package com.example.PowerToolStore.dto.request;

import com.example.PowerToolStore.constant.StockOperation;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
public class StockUpdateRequest {
    @NotNull
    private Long productId;

    @NotNull
    private StockOperation operation;

    @Min(1)
    private Integer quantity;
}
