package com.example.football.service.impl;

import com.example.football.models.dto.TeamSeedDto;
import com.example.football.models.entity.Team;
import com.example.football.repository.TeamRepository;
import com.example.football.service.TeamService;
import com.example.football.service.TownService;
import com.example.football.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final TownService townService;
    private static final String TEAMS_FILE_PATH = "src/main/resources/files/json/teams.json";
    private String fileContent;

    public TeamServiceImpl(TeamRepository teamRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil, TownService townService) {
        this.teamRepository = teamRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.townService = townService;
    }


    @Override
    public boolean areImported() {
        return this.teamRepository.count() > 0;
    }

    @Override
    public String readTeamsFileContent() throws IOException {
        fileContent = Files.readString(Path.of(TEAMS_FILE_PATH));
        return fileContent;
    }

    @Override
    public String importTeams() {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(gson.fromJson(fileContent, TeamSeedDto[].class))
                .filter(teamSeedDto -> {
                    boolean isValid = validationUtil.isValid(teamSeedDto)
                            && !this.teamRepository.existsByName(teamSeedDto.getName());

                    sb.append(isValid ? String.format("Successfully imported Team %s - %d", teamSeedDto.getName(), teamSeedDto.getFanBase())
                            : "Invalid Team")
                            .append(System.lineSeparator());
                    return isValid;
                })
                .map(teamSeedDto -> {
                    Team team = modelMapper.map(teamSeedDto, Team.class);
                    team.setTown(this.townService.findTownByName(teamSeedDto.getTownName()));
                    return team;
                })
                .forEach(this.teamRepository::save);

        return sb.toString();
    }

    @Override
    public Team findTeamByName(String name) {
        return this.teamRepository.findByName(name);
    }


}
