package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.PictureSeedDto;
import softuni.exam.models.entity.Picture;
import softuni.exam.repository.PictureRepository;
import softuni.exam.service.CarService;
import softuni.exam.service.PictureService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class PictureServiceImpl implements PictureService {
    private final ModelMapper modelMapper;
    private final PictureRepository pictureRepository;
    private final Gson gson;
    private static final String PICTURE_FILE_PATH = "src/main/resources/files/json/pictures.json";
    private final ValidationUtil validationUtil;
    private String fileContent;
    private final CarService carService;

    public PictureServiceImpl(ModelMapper modelMapper, PictureRepository pictureRepository, Gson gson, ValidationUtil validationUtil, CarService carService) {
        this.modelMapper = modelMapper;
        this.pictureRepository = pictureRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.carService = carService;
    }

    @Override
    public boolean areImported() {
        return this.pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesFromFile() throws IOException {
        fileContent = Files.readString(Path.of(PICTURE_FILE_PATH));
        return fileContent;
    }

    @Override
    public String importPictures() throws IOException {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(gson.fromJson(fileContent, PictureSeedDto[].class))
                .filter(pictureSeedDto -> {
                    boolean isValid = validationUtil.isValid(pictureSeedDto);
                    sb.append(isValid ? String.format("Successfully import picture - %s",pictureSeedDto.getName())
                            :"Invalid picture")
                            .append(System.lineSeparator());

                    return isValid;
                })
                .map(pictureSeedDto -> {
                    Picture picture = modelMapper.map(pictureSeedDto, Picture.class);
                    picture.setCar(this.carService.findById(pictureSeedDto.getCar()));
                    return picture;
                })
                .forEach(this.pictureRepository::save);


        return sb.toString();
    }
}
