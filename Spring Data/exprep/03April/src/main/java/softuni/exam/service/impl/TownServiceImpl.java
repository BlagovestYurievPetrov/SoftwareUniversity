package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.TownSeedDto;
import softuni.exam.models.entities.Town;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class TownServiceImpl implements TownService {
    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private static final String TOWNS_PATH_NAME = "src/main/resources/files/json/towns.json";
    private String fileContent;

    public TownServiceImpl(TownRepository townRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil) {
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        fileContent = Files.readString(Path.of(TOWNS_PATH_NAME));

        return fileContent;
    }

    @Override
    public String importTowns() {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(gson.fromJson(fileContent, TownSeedDto[].class))
        .filter(townSeedDto -> {
            boolean isValid = validationUtil.isValid(townSeedDto);
            sb.append(isValid ? String.format("Successfully imported Town %s - %d", townSeedDto.getName(), townSeedDto.getPopulation())
                    : "Invalid Town")
                    .append(System.lineSeparator());
            return isValid;
        })
        .map(townSeedDto -> modelMapper.map(townSeedDto, Town.class))
        .forEach(this.townRepository::save);


        return sb.toString();


    }

    @Override
    public boolean doesTownWithNameExist(String townName) {
        return this.townRepository.existsByName(townName);
    }

    @Override
    public Town findTownFromName(String townName) {
        return this.townRepository.findByName(townName);
    }
}
