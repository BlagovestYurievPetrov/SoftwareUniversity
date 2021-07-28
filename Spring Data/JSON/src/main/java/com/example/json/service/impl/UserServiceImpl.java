package com.example.json.service.impl;

import com.example.json.model.dto.UserSeedDto;
import com.example.json.model.entity.User;
import com.example.json.repository.UserRepository;
import com.example.json.service.UserService;
import com.example.json.util.ValidationUtilImpl;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final Gson gson;
    private static final String userSeedPath = "src/main/resources/files/users.json";
    private final ValidationUtilImpl validator;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, Gson gson, ValidationUtilImpl validator, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.gson = gson;
        this.validator = validator;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedUsers() throws IOException {
        if (this.userRepository.count() > 0) {
            return;
        }

        String fileContent = Files.readString(Path.of(userSeedPath));
        UserSeedDto[] userSeedDtos = gson.fromJson(fileContent,UserSeedDto[].class);

        Arrays.stream(userSeedDtos)
                .filter(validator::isValid)
                .map(userSeedDto -> modelMapper.map(userSeedDto, User.class))
                .forEach(this.userRepository::save);


    }

    @Override
    public User findRandom() {
        long randomId = ThreadLocalRandom.current().nextLong(1, userRepository.count() + 1);

        return this.userRepository.findById(randomId).orElse(null);
    }
}
