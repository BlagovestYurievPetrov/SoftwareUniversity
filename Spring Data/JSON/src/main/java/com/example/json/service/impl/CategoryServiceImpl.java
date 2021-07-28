package com.example.json.service.impl;

import com.example.json.model.dto.CategorySeedDto;
import com.example.json.model.entity.Category;
import com.example.json.repository.CategoryRepository;
import com.example.json.service.CategoryService;
import com.example.json.util.ValidationUtilImpl;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final Gson gson;
    private static final String categorySeedPath = "src/main/resources/files/categories.json";
    private final ValidationUtilImpl validator;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, Gson gson, ValidationUtilImpl validator, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.gson = gson;
        this.validator = validator;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedCategories() throws IOException {
        if(this.categoryRepository.count()>0){
            return;
        }
        String fileContent = Files.readString(Path.of(categorySeedPath));

        CategorySeedDto[] categorySeedDtos = gson.fromJson(fileContent, CategorySeedDto[].class);

        Arrays.stream(categorySeedDtos).filter(validator::isValid)
                .map(categorySeedDto -> modelMapper.map(categorySeedDto, Category.class))
                .forEach(categoryRepository::save);
    }

    @Override
    public Set<Category> findRandomCategories() {
        int catCount = ThreadLocalRandom
                .current().nextInt(1,3);

        Set<Category> categories = new HashSet<>();
        long totalCategories = this.categoryRepository.count();
        for (int i = 0; i < catCount; i++) {
            long randomId = ThreadLocalRandom.current().nextLong(1,totalCategories);

            categories.add(this.categoryRepository.findById(randomId).orElse(null));
        }
        return categories;
    }
}
