package com.example.mapping;

import com.example.mapping.model.dto.GameAddDto;
import com.example.mapping.model.dto.UserLoginDto;
import com.example.mapping.model.dto.UserRegisterDto;
import com.example.mapping.service.GameService;
import com.example.mapping.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;
@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final UserService userService;
    private final GameService gameService;

    public CommandLineRunnerImpl(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    @Override
    public void run(String... args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter command");
            String[] commands = scanner.nextLine().split("\\|");

                if(commands[0].equals("RegisterUser")) {this.userService.registerUser(new UserRegisterDto(commands[1],commands[2],commands[3],commands[4]));}
                if(commands[0].equals("LoginUser")) {this.userService.loginUser(new UserLoginDto(commands[1],commands[2]));}
                if(commands[0].equals("Logout")) {this.userService.logout();}
                if(commands[0].equals("AddGame")) {this.gameService.addGame(new GameAddDto(commands[1],new BigDecimal(commands[2]), Double.parseDouble(commands[3]),commands[4],commands[5],commands[6],commands[7]));}

        }
    }
}
