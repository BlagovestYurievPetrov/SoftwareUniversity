package com.example.mapping.service.impl;

import com.example.mapping.model.dto.GameAddDto;
import com.example.mapping.model.entity.Game;
import com.example.mapping.repository.GameRepository;
import com.example.mapping.service.GameService;
import com.example.mapping.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.Set;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final UserServiceImpl userService;

    public GameServiceImpl(GameRepository gameRepository, ModelMapper modelMapper, ValidationUtil validationUtil, UserServiceImpl userService) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.userService = userService;
    }


    @Override
    public void addGame(GameAddDto gameAddDto) {


        Set<ConstraintViolation<GameAddDto>> violations = validationUtil.violation(gameAddDto);

        if(!violations.isEmpty()){
            violations.stream().map(ConstraintViolation::getMessage).forEach(System.out::println);
            return;
        }

        Game game = modelMapper.map(gameAddDto,Game.class);

        this.gameRepository.save(game);
    }
}
