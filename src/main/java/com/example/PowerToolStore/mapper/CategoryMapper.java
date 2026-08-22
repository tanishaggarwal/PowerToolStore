package com.example.PowerToolStore.mapper;

import com.example.PowerToolStore.dto.request.category.CategoryCreateRequest;
import com.example.PowerToolStore.dto.request.category.CategoryUpdateRequest;
import com.example.PowerToolStore.dto.response.CategoryResponse;
import com.example.PowerToolStore.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category createEntity(CategoryCreateRequest createRequest)
    {
        return Category.builder()
                .categoryName(createRequest.getCategoryName())
                .description(createRequest.getDescription())
                .build();
    }

    public CategoryResponse toResponse(Category category)
    {
        return CategoryResponse.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .description(category.getDescription())
                .build();
    }

    public void updateEntity(Category category, CategoryUpdateRequest request)
    {
        category.setCategoryName(request.getCategoryName());
        category.setDescription(request.getDescription());
    }

}
