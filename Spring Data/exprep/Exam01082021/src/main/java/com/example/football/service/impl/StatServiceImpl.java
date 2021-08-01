package com.example.football.service.impl;

import com.example.football.models.dto.StatSeedDto;
import com.example.football.models.dto.StatSeedRootDto;
import com.example.football.models.entity.Stat;
import com.example.football.repository.StatRepository;
import com.example.football.service.StatService;
import com.example.football.util.ValidationUtil;
import com.example.football.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class StatServiceImpl implements StatService {
    private final StatRepository statRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private static final String STATS_FILE_PATH = "src/main/resources/files/xml/stats.xml";

    public StatServiceImpl(StatRepository statRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.statRepository = statRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }


    @Override
    public boolean areImported() {
        return this.statRepository.count() > 0;
    }

    @Override
    public String readStatsFileContent() throws IOException {
        return Files.readString(Path.of(STATS_FILE_PATH));
    }

    @Override
    public String importStats() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        xmlParser.fromFile(STATS_FILE_PATH, StatSeedRootDto.class).getStats()
                .stream().filter(statSeedDto -> {
                    boolean isValid = this.validationUtil.isValid(statSeedDto)
                            && !this.statRepository.existsByEnduranceAndPassingAndShooting(statSeedDto.getEndurance(), statSeedDto.getPassing(), statSeedDto.getShooting());
                    sb.append(isValid ? String.format("Successfully imported Stat %.2f - %.2f - %.2f", statSeedDto.getShooting(), statSeedDto.getPassing(), statSeedDto.getEndurance()) : "Invalid Stats")
                            .append(System.lineSeparator());

                    return isValid;

        })
                .map(statSeedDto -> modelMapper.map(statSeedDto, Stat.class))
                .forEach(this.statRepository::save);
        return sb.toString();
    }

    @Override
    public Stat findStatById(Long id) {
        return this.statRepository.findById(id).orElse(null);
    }
}
