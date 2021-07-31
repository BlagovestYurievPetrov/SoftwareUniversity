package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dto.PictureSeedDto;
import softuni.exam.instagraphlite.models.entity.Picture;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {
    private final PictureRepository pictureRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private static final String PICTURE_FILE_PATH = "src/main/resources/files/pictures.json";
    private String fileContent;

    public PictureServiceImpl(PictureRepository pictureRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.pictureRepository = pictureRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.pictureRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        fileContent = Files.readString(Path.of(PICTURE_FILE_PATH));
        return fileContent;
    }

    @Override
    public String importPictures() throws IOException {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(gson.fromJson(fileContent, PictureSeedDto[].class))
        .filter(pictureSeedDto -> {
            boolean isValid = validationUtil.isValid(pictureSeedDto)
                    && !doesPathExist(pictureSeedDto.getPath());

            sb.append(isValid ? String.format("Successfully imported Picture, with size %.2f", pictureSeedDto.getSize())
                    : "Invalid Picture");
            sb.append(System.lineSeparator());

            return isValid;
        })
                .map(pictureSeedDto -> modelMapper.map(pictureSeedDto, Picture.class))
                .forEach(this.pictureRepository::save);


        return sb.toString();
    }


    @Override
    public String exportPictures() {
        StringBuilder sb = new StringBuilder();
        List<Picture> allBySizeGreaterThanAndOrderBySize = this.pictureRepository.findAllBySizeGreaterThanOrderBySize(30000.0);
        allBySizeGreaterThanAndOrderBySize.forEach(picture -> {
            sb.append(String.format("%.2f – %s",picture.getSize(), picture.getPath())).append(System.lineSeparator());
        });


        return sb.toString();

    }

    @Override
    public boolean doesPathExist(String path) {
        return this.pictureRepository.existsByPath(path);
    }

    @Override
    public Picture findByPathName(String path) {
        return this.pictureRepository.findPictureByPath(path);
    }

}
