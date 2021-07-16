package com.example.demo.service;

import com.example.demo.model.entity.Category;
import com.example.demo.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;

    }


    @Override
    public void seedCategories() throws IOException {
        if(categoryRepository.count() > 0){
            return;
        }
        Files.readAllLines(Path.of("src/main/resources/files/categories.txt"))
                .stream()
                .filter(x -> !x.isEmpty())
                .forEach(row ->{
                    Category category = new Category(row);
                    categoryRepository.save(category);
                });

    }

    @Override
    public Set<Category> getRandomCategories() {
        Set<Category> categories = new HashSet<>();
        int randInt = ThreadLocalRandom.current().nextInt(1, 3);
        for (int i = 0; i < randInt; i++) {
            long randomId = ThreadLocalRandom.current().nextLong(1, categoryRepository.count() +1);
            Category category = categoryRepository.findById(randomId).orElse(null);
            categories.add(category);

        }

        return categories;
    }
}
