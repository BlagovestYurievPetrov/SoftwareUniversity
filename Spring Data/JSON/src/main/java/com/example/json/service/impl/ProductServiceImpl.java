package com.example.json.service.impl;

import com.example.json.model.dto.ProductSeedDto;
import com.example.json.model.entity.Product;
import com.example.json.repository.ProductRepository;
import com.example.json.service.ProductService;
import com.example.json.util.ValidationUtilImpl;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final UserServiceImpl userService;
    private final CategoryServiceImpl categoryService;
    private final Gson gson;
    private static final String productSeedPath = "src/main/resources/files/products.json";
    private final ValidationUtilImpl validator;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, UserServiceImpl userService, CategoryServiceImpl categoryService, Gson gson, ValidationUtilImpl validator, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.categoryService = categoryService;
        this.gson = gson;
        this.validator = validator;
        this.modelMapper = modelMapper;
    }


    @Override
    public void seedProducts() throws IOException {
        if (this.productRepository.count() > 0) {
            return;
        }
        String fileContent = Files.readString(Path.of(productSeedPath));

        ProductSeedDto[] productSeedDtos = gson.fromJson(fileContent, ProductSeedDto[].class);

        Arrays.stream(productSeedDtos)
                .filter(validator::isValid)
                .map(productSeedDto -> {
                    Product product = modelMapper.map(productSeedDto, Product.class);
                    product.setSeller(this.userService.findRandom());
                    if (product.getPrice().compareTo(BigDecimal.valueOf(500L)) > 0) {
                        product.setBuyer(this.userService.findRandom());
                    }
                    product.setCategories(this.categoryService.findRandomCategories());

                    return product;
                })
                .forEach(this.productRepository::save);
    }
}
