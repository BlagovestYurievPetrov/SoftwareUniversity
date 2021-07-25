package com.example.mapping.service;

import com.example.mapping.model.dto.GameAddDto;
import org.springframework.stereotype.Service;


public interface GameService {

    void addGame(GameAddDto gameAddDto);
}
