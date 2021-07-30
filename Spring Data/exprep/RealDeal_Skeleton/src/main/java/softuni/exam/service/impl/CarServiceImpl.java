package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CarSeedDto;
import softuni.exam.models.entity.Car;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private static final String CAR_FILE_PATH = "src/main/resources/files/json/cars.json";
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private String fileContent;

    public CarServiceImpl(CarRepository carRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return this.carRepository.count() > 0;
    }

    @Override
    public String readCarsFileContent() throws IOException {
        fileContent = Files.readString(Path.of(CAR_FILE_PATH));
        return fileContent;
    }

    @Override
    public String importCars() throws IOException {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(gson.fromJson(fileContent, CarSeedDto[].class))
        .filter(carSeedDto -> {
            boolean isValid = validationUtil.isValid(carSeedDto);
            sb.append(isValid ? String.format("Succesfully imported car %s - %s",carSeedDto.getMake(), carSeedDto.getModel())
                    :"Invalid car")
                    .append(System.lineSeparator());

            return isValid;
        })
                .map(carSeedDto -> modelMapper.map(carSeedDto, Car.class))
                .forEach(this.carRepository::save);


        return sb.toString();
    }

    @Override
    public String getCarsOrderByPicturesCountThenByMake() {
        return null;
    }
}
