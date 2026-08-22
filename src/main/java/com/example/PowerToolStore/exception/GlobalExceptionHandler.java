package com.example.PowerToolStore.exception;

import com.example.PowerToolStore.constant.MdcConstant;
import com.example.PowerToolStore.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(exception = {CategoryNotFoundException.class, ProductNotFoundException.class,
    UserNotFoundException.class, AddressNotFoundException.class, CartItemNotFoundException.class})
    public ResponseEntity<ErrorResponse> resourceNotFoundException( RuntimeException e)
    {
        log.warn("{}", e.toString(), e);

        return new ResponseEntity<>(
                buildErrorResponse(MDC.get(MdcConstant.REQUEST_ID), e),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(exception = ProductAlreadyInCartException.class)
    public ResponseEntity<ErrorResponse> productAlreadyInCartException(RuntimeException e)
    {
        log.warn("{}", e.toString(), e);

        return new ResponseEntity<>(
                buildErrorResponse(MDC.get(MdcConstant.REQUEST_ID), e),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(exception = {FileUploadException.class})
    public ResponseEntity<ErrorResponse> fileUploadException( RuntimeException e)
    {
        log.error("{}", e.toString(), e);

        return new ResponseEntity<>(
                buildErrorResponse(MDC.get(MdcConstant.REQUEST_ID), e),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(exception = {InsufficientStockException.class})
    public ResponseEntity<ErrorResponse> insufficientStockException( RuntimeException e)
    {
        log.error("{}", e.toString(), e);

        return new ResponseEntity<>(
                buildErrorResponse(MDC.get(MdcConstant.REQUEST_ID), e),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(exception = Exception.class)
    public ResponseEntity<ErrorResponse> unexpectedException( Exception e)
    {
        log.error("{}", e.toString(), e);

        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .message("An unexpected error occurred")
                        .requestId(MDC.get(MdcConstant.REQUEST_ID))
                        .build(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    private ErrorResponse buildErrorResponse(String requestId, RuntimeException e)
    {
        return ErrorResponse.builder()
                .requestId(requestId)
                .message(e.getMessage())
                .build();
    }

}
