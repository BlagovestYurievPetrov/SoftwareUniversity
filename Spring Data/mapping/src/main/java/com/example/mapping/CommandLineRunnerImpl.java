package com.example.mapping;

import com.example.mapping.model.dto.UserRegisterDto;
import com.example.mapping.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final UserService userService;

    public CommandLineRunnerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter command");
            String[] commands = scanner.nextLine().split("\\|");

            switch (commands[0]){
                case "RegisterUser": this.userService.registerUser(new UserRegisterDto(commands[1],commands[2],commands[3],commands[4]));
            }
        }
    }
}
