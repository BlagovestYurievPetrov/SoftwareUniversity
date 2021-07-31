package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.OfferSeedRootDto;
import softuni.exam.models.entity.Offer;
import softuni.exam.repository.OfferRepository;
import softuni.exam.service.CarService;
import softuni.exam.service.OfferService;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private static final String OFFER_FILE_PATH = "src/main/resources/files/xml/offers.xml";
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private String fileContent;
    private final SellerService sellerService;
    private final CarService carService;

    public OfferServiceImpl(OfferRepository offerRepository, ValidationUtil validationUtil, ModelMapper modelMapper, XmlParser xmlParser, SellerService sellerService, CarService carService) {
        this.offerRepository = offerRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.sellerService = sellerService;
        this.carService = carService;
    }

    @Override
    public boolean areImported() {
        return this.offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        fileContent = Files.readString(Path.of(OFFER_FILE_PATH));
        return fileContent;
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        xmlParser.fromFile(OFFER_FILE_PATH, OfferSeedRootDto.class)
                .getOffers()
                .stream().filter(offerSeedDto -> {
                    boolean isValid = validationUtil.isValid(offerSeedDto);
                    sb.append(isValid ? String.format("Successfully import offer %s - %s",
                            offerSeedDto.getAddedOn(), offerSeedDto.getHasGoldStatus()):"Invalid offer");
                    sb.append(System.lineSeparator());
                    return isValid;
        })
                .map(offerSeedDto -> {
                    Offer offer = modelMapper.map(offerSeedDto, Offer.class);
                    offer.setSeller(this.sellerService.findById(offerSeedDto.getSeller().getId()));
                    offer.setCar(this.carService.findById(offerSeedDto.getCar().getId()));
                    return offer;
                })
                .forEach(this.offerRepository::save);
        return sb.toString();
    }
}
