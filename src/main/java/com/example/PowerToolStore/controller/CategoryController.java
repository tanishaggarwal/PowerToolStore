package com.example.PowerToolStore.controller;

import com.example.PowerToolStore.constant.MdcConstant;
import com.example.PowerToolStore.dto.request.category.CategoryCreateRequest;
import com.example.PowerToolStore.dto.request.category.CategoryUpdateRequest;
import com.example.PowerToolStore.dto.response.CategoryResponse;
import com.example.PowerToolStore.dto.response.ApiResponse;
import com.example.PowerToolStore.dto.response.GenericResponse;
import com.example.PowerToolStore.service.CategoryService;
import jakarta.validation.Valid;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> findById(@PathVariable Long id)
    {
        return new ResponseEntity<>(
                ApiResponse.<CategoryResponse>builder()
                        .data(categoryService.findById(id))
                        .requestId(MDC.get(MdcConstant.REQUEST_ID))
                        .build(),
                HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> findAll()
    {
        return new ResponseEntity<>(
                ApiResponse.<List<CategoryResponse>>builder()
                        .data(categoryService.findAllCategories())
                        .requestId(MDC.get(MdcConstant.REQUEST_ID))
                        .build()
                ,
                HttpStatus.OK
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<GenericResponse>> createCategory(
            @RequestBody @Valid CategoryCreateRequest request)
    {
        categoryService.createCategory(request);
        return new ResponseEntity<>(
                ApiResponse.<GenericResponse>builder()
                        .data(GenericResponse.builder().message("Category Created Successfully").build())
                        .requestId(MDC.get(MdcConstant.REQUEST_ID))
                        .build()
                , HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<ApiResponse<GenericResponse>> updateCategory(
            @RequestBody @Valid CategoryUpdateRequest request)
    {
        categoryService.updateCategory(request);
        return new ResponseEntity<>(ApiResponse.<GenericResponse>builder()
                    .data(GenericResponse.builder().message("Category Updated Successfully").build())
                    .requestId(MDC.get(MdcConstant.REQUEST_ID))
                    .build(),
                HttpStatus.OK);
    }
}
