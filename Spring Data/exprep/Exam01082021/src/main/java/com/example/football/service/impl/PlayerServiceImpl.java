package com.example.football.service.impl;

import com.example.football.models.dto.PlayerSeedRootDto;
import com.example.football.models.entity.Player;
import com.example.football.repository.PlayerRepository;
import com.example.football.service.PlayerService;
import com.example.football.service.StatService;
import com.example.football.service.TeamService;
import com.example.football.service.TownService;
import com.example.football.util.ValidationUtil;
import com.example.football.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamService teamService;
    private final TownService townService;
    private final StatService statService;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private static final String PLAYERS_FILE_PATH = "src/main/resources/files/xml/players.xml";

    public PlayerServiceImpl(PlayerRepository playerRepository, TeamService teamService, TownService townService, StatService statService, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.playerRepository = playerRepository;
        this.teamService = teamService;
        this.townService = townService;
        this.statService = statService;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean areImported() {
        return this.playerRepository.count() > 0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {
        return Files.readString(Path.of(PLAYERS_FILE_PATH));
    }

    @Override
    public String importPlayers() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        xmlParser.fromFile(PLAYERS_FILE_PATH, PlayerSeedRootDto.class).getPlayers()
        .stream().filter(playerSeedDto -> {
            boolean isValid = this.validationUtil.isValid(playerSeedDto)
                    && !this.playerRepository.existsByEmail(playerSeedDto.getEmail());
            sb.append(isValid ? String.format("Successfully imported Player %s %s - %s", playerSeedDto.getFirstName(), playerSeedDto.getLastName(), playerSeedDto.getPosition())
                    : "Invalid Player")
                    .append(System.lineSeparator());

            return isValid;
        })
                .map(playerSeedDto -> {
                    Player player = modelMapper.map(playerSeedDto, Player.class);
                    player.setTeam(this.teamService.findTeamByName(playerSeedDto.getTeamNameDto().getName()));
                    player.setTown(this.townService.findTownByName(playerSeedDto.getTownNameDto().getName()));
                    player.setStats(this.statService.findStatById(playerSeedDto.getStatIdDto().getId()));
                    return player;
                })
                .forEach(this.playerRepository::save);
        return sb.toString();
    }

    @Override
    public String exportBestPlayers() {
        StringBuilder sb = new StringBuilder();
        List<Player> players = this.playerRepository.findAllByBirthDateOrderByStatsAndLastName(LocalDate.of(1995,1,1), LocalDate.of(2003,1,1));
        players.forEach( player -> {
            sb.append(String.format("Player - %s %s",player.getFirstName(),player.getLastName()));
            sb.append(System.lineSeparator());
            sb.append(String.format("\tPosition - %s", player.getPosition()));
            sb.append(System.lineSeparator());
            sb.append(String.format("\tTeam - %s", player.getTeam().getName()));
            sb.append(System.lineSeparator());
            sb.append(String.format("\tStadium - %s", player.getTeam().getStadiumName()));
            sb.append(System.lineSeparator());
        });
        return sb.toString();
    }
}
