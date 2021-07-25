package com.example.mapping.service.impl;

import com.example.mapping.model.dto.UserLoginDto;
import com.example.mapping.model.dto.UserRegisterDto;
import com.example.mapping.model.entity.User;
import com.example.mapping.repository.UserRepository;
import com.example.mapping.service.UserService;
import com.example.mapping.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private User loggedInUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void registerUser(UserRegisterDto userRegisterDto) {
        if(!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())){
            System.out.println("Wrong confirm password");
            return;
        }
        Set<ConstraintViolation<UserRegisterDto>> violations = validationUtil.violation(userRegisterDto);

        if(!violations.isEmpty()){
            violations.stream().map(ConstraintViolation::getMessage).forEach(System.out::println);
            return;
        }

        User user = modelMapper.map(userRegisterDto, User.class);

        this.userRepository.save(user);
    }

    @Override
    public void loginUser(UserLoginDto userLoginDto) {
        Set<ConstraintViolation<UserLoginDto>> violations = validationUtil.violation(userLoginDto);

        if(!violations.isEmpty()){
            violations.stream().map(ConstraintViolation::getMessage).forEach(System.out::println);
            return;
        }

        User user = this.userRepository.findByEmailAndPassword(userLoginDto.getEmail(),userLoginDto.getPassword()).orElse(null);

        if (user==null){
            System.out.println("This user doesnt exist");
            return;
        }

        this.loggedInUser = user;
        System.out.println(user.getFullName() + " logged in.");
    }

    @Override
    public void logout() {
        if(this.loggedInUser == null){
            System.out.println("No one is logged in.");
        } else {
            String name = this.loggedInUser.getFullName();
            this.loggedInUser = null;
            System.out.println(name + " logged out.");
        }

    }
}
