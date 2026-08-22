package com.example.PowerToolStore.mapper;

import com.example.PowerToolStore.dto.request.product.ProductCreateRequest;
import com.example.PowerToolStore.dto.request.product.ProductStatusUpdateRequest;
import com.example.PowerToolStore.dto.request.product.ProductUpdateRequest;
import com.example.PowerToolStore.dto.response.ProductResponse;
import com.example.PowerToolStore.entity.Category;
import com.example.PowerToolStore.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public void updateEntity(Product product, ProductUpdateRequest productUpdateRequest,
                             Category category)
    {
        product.setBrand(productUpdateRequest.getBrand());
        product.setCategory(category);
        product.setDescription(productUpdateRequest.getDescription());
        product.setTitle(productUpdateRequest.getTitle());
        product.setDiscountPercent(productUpdateRequest.getDiscountPercent());
        product.setMaxRetailPrice(productUpdateRequest.getMaxRetailPrice());
    }

    public void updateEntity(Product product, ProductStatusUpdateRequest productStatusUpdateRequest)
    {
        product.setIsActive(productStatusUpdateRequest.getIsActive());
    }

    public ProductResponse toResponse(Product product)
    {
        return ProductResponse.builder()
                .productId(product.getProductId())
                .brand(product.getBrand())
                .categoryName(product.getCategory().getCategoryName())
                .description(product.getDescription())
                .title(product.getTitle())
                .createdAt(product.getCreatedAt())
                .discountPercent(product.getDiscountPercent())
                .maxRetailPrice(product.getMaxRetailPrice())
                .isActive(product.getIsActive())
                .imageUrl(product.getImageUrl())
                .quantityInStock(product.getQuantityInStock())
                .build();
    }

    public Product createEntity(ProductCreateRequest productCreateRequest, Category category, String imageURL)
    {
        return Product.builder()
                .brand(productCreateRequest.getBrand())
                .imageUrl(imageURL)
                .title(productCreateRequest.getTitle())
                .description(productCreateRequest.getDescription())
                .maxRetailPrice(productCreateRequest.getMaxRetailPrice())
                .category(category)
                .quantityInStock(productCreateRequest.getQuantityInStock())
                .discountPercent(productCreateRequest.getDiscountPercent())
                .isActive(productCreateRequest.getIsActive())
                .build();
    }
}
