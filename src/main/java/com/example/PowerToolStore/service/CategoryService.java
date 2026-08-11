package com.example.PowerToolStore.service;

import com.example.PowerToolStore.dto.request.CategoryCreateRequest;
import com.example.PowerToolStore.dto.request.CategoryUpdateRequest;
import com.example.PowerToolStore.dto.response.CategoryResponse;
import com.example.PowerToolStore.entity.Category;
import com.example.PowerToolStore.exception.CategoryNotFoundException;
import com.example.PowerToolStore.mapper.CategoryMapper;
import com.example.PowerToolStore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper mapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public boolean createCategory(CategoryCreateRequest request)
    {
        Category category = mapper.createEntity(request);
        // save the entity in db
        categoryRepository.save(category);
        return true;
    }

    @Transactional
    public boolean updateCategory(CategoryUpdateRequest request)
    {
        Optional<Category> category = categoryRepository.findById(request.getCategoryId());

        if(category.isPresent())
        {
            mapper.updateEntity(category.get(), request);
            // save the updated entity in db
            categoryRepository.save(category.get());
            return true;
        }
        else{
            throw new CategoryNotFoundException(request.getCategoryId());
        }
    }

    @Transactional
    public CategoryResponse findById(Long categoryId)
    {
        Optional<Category> category = categoryRepository.findById(categoryId);

        if(category.isPresent())
        {
            return mapper.toResponse(category.get());
        }
        else{
            throw new CategoryNotFoundException(categoryId);
        }
    }

    @Transactional
    public List<CategoryResponse> findAllCategories()
    {
        List<CategoryResponse> response = new ArrayList<>();
        for(Category category: categoryRepository.findAll())
        {
            response.add(mapper.toResponse(category));
        }
        return response;
    }
}
