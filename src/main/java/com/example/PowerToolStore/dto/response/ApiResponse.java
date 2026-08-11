package com.example.PowerToolStore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class ApiResponse<T> {
    private T data;
    private String requestId;
}
