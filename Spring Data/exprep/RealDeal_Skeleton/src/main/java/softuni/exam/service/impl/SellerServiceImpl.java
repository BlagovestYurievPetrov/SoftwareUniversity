package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.SellerSeedRootDto;
import softuni.exam.models.entity.Seller;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class SellerServiceImpl implements SellerService {
    private final SellerRepository sellerRepository;
    private static final String SELLER_FILE_PATH = "src/main/resources/files/xml/sellers.xml";
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;
    private String fileContent;
    public SellerServiceImpl(SellerRepository sellerRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser) {
        this.sellerRepository = sellerRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return this.sellerRepository.count() > 0;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        fileContent = Files.readString(Path.of(SELLER_FILE_PATH));
        return fileContent;
    }

    @Override
    public String importSellers() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        xmlParser.fromFile(SELLER_FILE_PATH, SellerSeedRootDto.class)
        .getSellers()
        .stream().filter(sellerSeedDto -> {
            boolean isValid = validationUtil.isValid(sellerSeedDto);
            sb.append(isValid ? String.format("Successfully import seller %s - %s", sellerSeedDto.getLastName(), sellerSeedDto.getEmail()) : "Invalid seller");
            sb.append(System.lineSeparator());
            return isValid;
        })
                .map(sellerSeedDto -> this.modelMapper.map(sellerSeedDto, Seller.class))
                .forEach(this.sellerRepository::save);



        return sb.toString();
    }

    @Override
    public Seller findById(Integer id) {
        return this.sellerRepository.findById(id).orElse(null);
    }
}
