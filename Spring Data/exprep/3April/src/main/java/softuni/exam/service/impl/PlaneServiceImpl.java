package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.PlanesSeedDto;
import softuni.exam.models.dto.PlanesSeedRootDto;
import softuni.exam.models.entities.Plane;
import softuni.exam.repository.PlaneRepository;
import softuni.exam.service.PlaneService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class PlaneServiceImpl implements PlaneService {
    private final PlaneRepository planeRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;
    private static final String PLANES_PATH_NAME = "src/main/resources/files/xml/planes.xml";

    public PlaneServiceImpl(PlaneRepository planeRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser) {
        this.planeRepository = planeRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return this.planeRepository.count() > 0;
    }

    @Override
    public String readPlanesFileContent() throws IOException {
        return Files.readString(Path.of(PLANES_PATH_NAME));
    }

    @Override
    public String importPlanes() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        xmlParser.fromFile(PLANES_PATH_NAME, PlanesSeedRootDto.class).getPlanes()
                .stream().filter(planesSeedDto -> {
                    boolean isValid = validationUtil.isValid(planesSeedDto)
                            && !this.planeRepository.existsByRegisterNumber(planesSeedDto.getRegisterNumber());
                    sb.append(isValid ? String.format("Successfully imported Plane %s",planesSeedDto.getRegisterNumber()) : "Invalid Plane")
                            .append(System.lineSeparator());

                    return isValid;
        })
                .map(planesSeedDto -> modelMapper.map(planesSeedDto, Plane.class))
                .forEach(this.planeRepository::save);

        return sb.toString();
    }

    @Override
    public boolean doesPlaneExistWithRegisterNumber(String registerNumber) {
        return this.planeRepository.existsByRegisterNumber(registerNumber);

    }

    @Override
    public Plane findPlaneByRegisterNumber(String registerNumber) {
        return this.planeRepository.findByRegisterNumber(registerNumber);
    }
}
