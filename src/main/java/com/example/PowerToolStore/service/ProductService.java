package com.example.PowerToolStore.service;

import com.example.PowerToolStore.constant.StockOperation;
import com.example.PowerToolStore.dto.request.ProductCreateRequest;
import com.example.PowerToolStore.dto.request.ProductStatusUpdateRequest;
import com.example.PowerToolStore.dto.request.ProductUpdateRequest;
import com.example.PowerToolStore.dto.request.StockUpdateRequest;
import com.example.PowerToolStore.dto.response.ProductResponse;
import com.example.PowerToolStore.entity.Category;
import com.example.PowerToolStore.entity.Product;
import com.example.PowerToolStore.exception.CategoryNotFoundException;
import com.example.PowerToolStore.exception.FileUploadException;
import com.example.PowerToolStore.exception.ProductNotFoundException;
import com.example.PowerToolStore.mapper.ProductMapper;
import com.example.PowerToolStore.repository.CategoryRepository;
import com.example.PowerToolStore.repository.ProductRepository;
import com.example.PowerToolStore.util.FileUploadUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// TO DO :- 1 - remove try catch and simply throw exceptions and
// catch it as part of GlobalExceptionHandler (A single place for entire application's exception handling)

// 2 - create a method to allow product image change

// 3 - implement something to enable/disable a product based on stock in inventory

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private FileUploadUtility fileUploadUtility;

    @Transactional
    public boolean createNewProduct(ProductCreateRequest createRequest) throws Exception {
        Optional<Category> category = categoryRepository.findById(createRequest.getCategoryId());
        if (category.isPresent()) {

            Map imageUploadResponse;

            try {
                // upload product Image
                imageUploadResponse = fileUploadUtility.uploadFile(createRequest.getFile());
                log.info("Image Uploaded Successfully for product: {} brand: {}",
                        createRequest.getTitle(),
                        createRequest.getBrand());
            }
            catch (FileUploadException e)
            {
                log.error("Image upload failed while creating product : {}, brand: {}",
                        createRequest.getTitle(), createRequest.getBrand());
                throw e;
            }

            Product product = productMapper.createEntity(
                    createRequest,
                    category.get(),
                    imageUploadResponse.get("url").toString());

            // saving new product in db
            productRepository.save(product);
            log.info("Product created");
            return true;
        }
        else{
            throw new CategoryNotFoundException(createRequest.getCategoryId());
        }

    }

    @Transactional
    public boolean updateProductDetails(ProductUpdateRequest updateRequest) throws Exception {
        Optional<Category> category = categoryRepository
                .findById(updateRequest.getCategoryId());

        if(category.isPresent())
        {
            Optional<Product> product = productRepository
                    .findById(updateRequest.getProductId());

            if(product.isPresent())
            {
                productMapper.updateEntity(product.get(), updateRequest, category.get());
                // saving updated product in db
                productRepository.save(product.get());
                log.info("Updated product details");
                return true;
            }
            else{
                throw new ProductNotFoundException(updateRequest.getProductId());
            }
        }
        else {
            throw new CategoryNotFoundException(updateRequest.getCategoryId());
        }
    }

    @Transactional
    public boolean updateStock(StockUpdateRequest updateRequest) throws Exception {

        Optional<Product> product = productRepository
                .findById(updateRequest.getProductId());

        if(product.isPresent())
        {
            if (updateRequest.getOperation() == StockOperation.DECREMENT) {
                product.get().decreaseStock(updateRequest.getQuantity());
            }
            else {
                product.get().increaseStock(updateRequest.getQuantity());
            }

            // saving updated product in db
            productRepository.save(product.get());
            log.info("stock updated");
            return true;
        }
        else {
            throw new ProductNotFoundException(updateRequest.getProductId());
        }
    }

    @Transactional
    public boolean updateStatus(ProductStatusUpdateRequest updateRequest) throws Exception {

        Optional<Product> product = productRepository
                .findById(updateRequest.getProductId());

        if(product.isPresent())
        {
            productMapper.updateEntity(product.get(), updateRequest);
            // saving product with updated status in db
            productRepository.save(product.get());
            log.info("status updated");
            return true;
        }
        else {
            throw new ProductNotFoundException(updateRequest.getProductId());
        }

    }

    @Transactional
    public ProductResponse findByProductId(Long productId)
    {
        Optional<Product> product = productRepository.findById(productId);

        if(product.isPresent())
        {
            return productMapper.toResponse(product.get());
        }
        else {
            throw new ProductNotFoundException(productId);
        }
    }

    @Transactional
    public List<ProductResponse> findAllProducts()
    {
        List<ProductResponse> allProducts = new ArrayList<>();

        for(Product product: productRepository.findAll())
        {
            allProducts.add(productMapper.toResponse(product));
        }
        return allProducts;
    }

}
