package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.PassengerSeedDto;
import softuni.exam.models.entities.Passenger;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.service.PassengerService;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class PassengerServiceImpl implements PassengerService {
    private final PassengerRepository passengerRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;
    private static final String PASSENGER_PATH_NAME = "src/main/resources/files/json/passengers.json";
    private String fileContent;
    private final TownService townService;

    public PassengerServiceImpl(PassengerRepository passengerRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson, TownService townService) {
        this.passengerRepository = passengerRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.townService = townService;
    }

    @Override
    public boolean areImported() {
        return this.passengerRepository.count() > 0;
    }

    @Override
    public String readPassengersFileContent() throws IOException {
        fileContent = Files.readString(Path.of(PASSENGER_PATH_NAME));
        return fileContent;
    }

    @Override
    public String importPassengers() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        Arrays.stream(gson.fromJson(fileContent, PassengerSeedDto[].class))
                .filter(passengerSeedDto -> {
                    boolean isValid = validationUtil.isValid(passengerSeedDto)
                            && this.townService.doesTownWithNameExist(passengerSeedDto.getTown());
                    stringBuilder.append(isValid ? String.format("Successfully imported Passenger %s - %s",passengerSeedDto.getLastName(), passengerSeedDto.getEmail())
                            : "Invalid Passenger")
                            .append(System.lineSeparator());
                    return isValid;
                })
                .map(passengerSeedDto -> {
                    Passenger passenger = modelMapper.map(passengerSeedDto, Passenger.class);
                    passenger.setTown(this.townService.findTownFromName(passengerSeedDto.getTown()));
                    return passenger;
                })
                .forEach(this.passengerRepository::save);

        return stringBuilder.toString();
    }

    @Override
    public String getPassengersOrderByTicketsCountDescendingThenByEmail() {
        StringBuilder sb = new StringBuilder();

        this.passengerRepository.getAllPassengersOrderedByTicketsCountAndEmail()
                .forEach(passenger -> {
                    sb.append(String.format("Passenger %s  %s",passenger.getFirstName(), passenger.getLastName())).append(System.lineSeparator())
                            .append(String.format("\tEmail - %s",passenger.getEmail())).append(System.lineSeparator())
                            .append(String.format("\tPhone - %s",passenger.getPhoneNumber())).append(System.lineSeparator())
                            .append(String.format("\tNumber of tickets - %d",passenger.getTickets().size())).append(System.lineSeparator());
                });



        return sb.toString();

    }

    @Override
    public boolean doesPassengerExist(String email) {
        return this.passengerRepository.existsByEmail(email);

    }

    @Override
    public Passenger findPassengerByEmail(String email) {
        return this.passengerRepository.findByEmail(email);


    }
}
