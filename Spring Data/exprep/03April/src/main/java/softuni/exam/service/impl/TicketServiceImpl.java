package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.TicketSeedRootDto;
import softuni.exam.models.entities.Ticket;
import softuni.exam.repository.TicketRepository;
import softuni.exam.service.PassengerService;
import softuni.exam.service.PlaneService;
import softuni.exam.service.TicketService;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final TownService townService;
    private final PassengerService passengerService;
    private final PlaneService planeService;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private static final String TICKETS_FILE_PATH = "src/main/resources/files/xml/tickets.xml";

    public TicketServiceImpl(TicketRepository ticketRepository, TownService townService, PassengerService passengerService, PlaneService planeService, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validationUtil) {
        this.ticketRepository = ticketRepository;
        this.townService = townService;
        this.passengerService = passengerService;
        this.planeService = planeService;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.ticketRepository.count() > 0;
    }

    @Override
    public String readTicketsFileContent() throws IOException {
        return Files.readString(Path.of(TICKETS_FILE_PATH));
    }


    @Override
    public String importTickets() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        xmlParser.fromFile(TICKETS_FILE_PATH, TicketSeedRootDto.class).getTickets()
        .stream().filter(ticketSeedDto -> {
            boolean isValid = validationUtil.isValid(ticketSeedDto)
                    && this.townService.doesTownWithNameExist(ticketSeedDto.getFromTownDto().getName())
                    && this.townService.doesTownWithNameExist(ticketSeedDto.getToTownDto().getName())
                    && this.passengerService.doesPassengerExist(ticketSeedDto.getPassengerEmailDto().getEmail())
                    && this.planeService.doesPlaneExistWithRegisterNumber(ticketSeedDto.getPlaneRegisterNumberDto().getRegisterNumber());

            sb.append(isValid ? String.format("Successfully imported Ticket %s - %s",ticketSeedDto.getFromTownDto().getName(), ticketSeedDto.getToTownDto().getName()) : "Invalid Ticket")
                    .append(System.lineSeparator());

            return isValid;
        })
                .map(ticketSeedDto -> {
                    Ticket ticket = modelMapper.map(ticketSeedDto, Ticket.class);
                    ticket.setFromTown(this.townService.findTownFromName(ticketSeedDto.getFromTownDto().getName()));
                    ticket.setToTown(this.townService.findTownFromName(ticketSeedDto.getToTownDto().getName()));
                    ticket.setPassenger(this.passengerService.findPassengerByEmail(ticketSeedDto.getPassengerEmailDto().getEmail()));
                    ticket.setPlane(this.planeService.findPlaneByRegisterNumber(ticketSeedDto.getPlaneRegisterNumberDto().getRegisterNumber()));
                    return ticket;
                })
                .forEach(this.ticketRepository::save);

        return sb.toString();
    }
}
