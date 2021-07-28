package com.example.json.service.impl;

import com.example.json.repository.ProductRepository;
import com.example.json.service.ProductService;
import com.example.json.util.ValidationUtilImpl;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final Gson gson;
    private static final String productSeedPath = "src/main/resources/files/products.json";
    private final ValidationUtilImpl validator;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, Gson gson, ValidationUtilImpl validator, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.gson = gson;
        this.validator = validator;
        this.modelMapper = modelMapper;
    }


    @Override
    public void seedProducts() {
    }
}
