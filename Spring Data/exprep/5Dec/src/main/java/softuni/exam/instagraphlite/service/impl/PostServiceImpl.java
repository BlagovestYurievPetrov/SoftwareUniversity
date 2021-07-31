package softuni.exam.instagraphlite.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dto.PostSeedRootDto;
import softuni.exam.instagraphlite.models.entity.Post;
import softuni.exam.instagraphlite.repository.PostRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.service.PostService;
import softuni.exam.instagraphlite.service.UserService;
import softuni.exam.instagraphlite.util.ValidationUtil;
import softuni.exam.instagraphlite.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    private final PictureService pictureService;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private static final String POST_FILE_PATH = "src/main/resources/files/posts.xml";

    public PostServiceImpl(PostRepository postRepository, UserService userService, PictureService pictureService, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.pictureService = pictureService;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.postRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(Path.of(POST_FILE_PATH));
    }

    @Override
    public String importPosts() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        xmlParser.fromFile(POST_FILE_PATH, PostSeedRootDto.class)
                .getPosts()
        .stream().filter(postSeedDto -> {
            boolean isValid = validationUtil.isValid(postSeedDto)
                    && this.userService.doesUsernameExist(postSeedDto.getUsernameDto().getUsername())
                    && this.pictureService.doesPathExist(postSeedDto.getPictureDto().getPath());
                sb.append(isValid ? String.format("Successfully imported Post, made by %s",postSeedDto.getUsernameDto().getUsername()) : "Invalid post")
                        .append(System.lineSeparator());
            return isValid;

        })
        .map(postSeedDto -> {
            Post post = modelMapper.map(postSeedDto, Post.class);
            post.setPicture(this.pictureService.findByPathName(postSeedDto.getPictureDto().getPath()));
            post.setUser(this.userService.findUserByUserName(postSeedDto.getUsernameDto().getUsername()));
            return post;
        })
        .forEach(this.postRepository::save);


        return sb.toString();
    }
}
