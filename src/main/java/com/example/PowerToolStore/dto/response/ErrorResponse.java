package com.example.PowerToolStore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private String requestId;
}
