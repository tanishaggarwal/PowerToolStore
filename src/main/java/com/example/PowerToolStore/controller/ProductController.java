package com.example.PowerToolStore.controller;

import com.example.PowerToolStore.constant.MdcConstant;
import com.example.PowerToolStore.dto.request.product.ProductCreateRequest;
import com.example.PowerToolStore.dto.request.product.ProductStatusUpdateRequest;
import com.example.PowerToolStore.dto.request.product.ProductUpdateRequest;
import com.example.PowerToolStore.dto.request.product.StockUpdateRequest;
import com.example.PowerToolStore.dto.response.ApiResponse;
import com.example.PowerToolStore.dto.response.GenericResponse;
import com.example.PowerToolStore.dto.response.ProductResponse;
import com.example.PowerToolStore.service.ProductService;
import jakarta.validation.Valid;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<GenericResponse>> createProduct(@Valid @ModelAttribute ProductCreateRequest createRequest) throws Exception {

        productService.createNewProduct(createRequest);
        return new ResponseEntity<>(
                ApiResponse.<GenericResponse>builder()
                        .data(GenericResponse.builder().message("Product Created Successfully").build())
                        .requestId(MDC.get(MdcConstant.REQUEST_ID))
                        .build()
                , HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/update-details")
    public ResponseEntity<ApiResponse<GenericResponse>> updateProductDetails(@Valid @RequestBody ProductUpdateRequest productUpdateRequest
    ) throws Exception {

        productService.updateProductDetails(productUpdateRequest);
        return new ResponseEntity<>(
                ApiResponse.<GenericResponse>builder()
                        .data(GenericResponse.builder().message("Product Details Updated Successfully").build())
                        .requestId(MDC.get(MdcConstant.REQUEST_ID))
                        .build(),
                HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update-status")
    public ResponseEntity<ApiResponse<GenericResponse>> updateProductStatus(@Valid @RequestBody ProductStatusUpdateRequest statusUpdateRequest
    ) throws Exception {

        productService.updateStatus(statusUpdateRequest);
        return new ResponseEntity<>(
                ApiResponse.<GenericResponse>builder()
                        .data(GenericResponse.builder().message("Product Status Updated Successfully").build())
                        .requestId(MDC.get(MdcConstant.REQUEST_ID))
                        .build()
                ,HttpStatus.OK);
    }

    @PutMapping("/update-stock")
    public ResponseEntity<ApiResponse<GenericResponse>> updateStock(@Valid @RequestBody StockUpdateRequest stockUpdateRequest
    ) throws Exception {

        productService.updateStock(stockUpdateRequest);
        return new ResponseEntity<>(
                ApiResponse.<GenericResponse>builder()
                        .data(GenericResponse.builder().message("Stock updated successfully").build())
                        .requestId(MDC.get(MdcConstant.REQUEST_ID))
                        .build()
                , HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> getProduct(@PathVariable Long id){

        return new ResponseEntity<>(
                ApiResponse.<ProductResponse>builder()
                        .data(productService.findByProductId(id))
                        .requestId(MDC.get(MdcConstant.REQUEST_ID))
                        .build(),
                HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getAllProducts(){

        return new ResponseEntity<>(
                ApiResponse.<List<ProductResponse>>builder()
                        .data(productService.findAllProducts())
                        .requestId(MDC.get(MdcConstant.REQUEST_ID))
                        .build(),
                HttpStatus.OK);
    }
}
