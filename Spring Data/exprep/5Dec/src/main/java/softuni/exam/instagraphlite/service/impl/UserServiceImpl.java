package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dto.UserSeedDto;
import softuni.exam.instagraphlite.models.entity.Post;
import softuni.exam.instagraphlite.models.entity.User;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.service.UserService;
import softuni.exam.instagraphlite.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PictureService pictureService;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private static final String USER_FILE_PATH = "src/main/resources/files/users.json";
    private String fileContent;

    public UserServiceImpl(UserRepository userRepository, PictureService pictureService, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.pictureService = pictureService;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }


    @Override
    public boolean areImported() {
        return this.userRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        fileContent = Files.readString(Path.of(USER_FILE_PATH));
        return fileContent;
    }

    @Override
    public String importUsers() throws IOException {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(gson.fromJson(fileContent, UserSeedDto[].class))
        .filter(userSeedDto -> {
            boolean isValid = validationUtil.isValid(userSeedDto)
                    && !doesUsernameExist(userSeedDto.getUsername())
                    && this.pictureService.doesPathExist(userSeedDto.getProfilePicture());
            sb.append(isValid ? "Successfully imported User: " + userSeedDto.getUsername() : "Invalid user")
                    .append(System.lineSeparator());


            return isValid;
        })
                .map(userSeedDto -> modelMapper.map(userSeedDto, User.class))
                .forEach(this.userRepository::save);

        return sb.toString();
    }





    @Override
    public String exportUsersWithTheirPosts() {
        StringBuilder sb = new StringBuilder();
        this.userRepository.allUsersWithPosts()
                .forEach(user -> {

            sb.append(String.format("User: %s",user.getUsername()));
            sb.append(System.lineSeparator());
            sb.append(String.format("Post count: %d", user.getPosts().size()));
            sb.append(System.lineSeparator());
            Set<Post> thisUserPosts = user.getPosts();
                    thisUserPosts.stream().sorted(Comparator.comparing(a -> a.getPicture().getSize()))
                            .forEach(post -> {
                                sb.append("==Post Details:");
                                sb.append(System.lineSeparator());
                                sb.append(String.format("----Caption: %s", post.getCaption()));
                                sb.append(System.lineSeparator());
                                sb.append(String.format("----Picture Size: %.2f", post.getPicture().getSize()));
                                sb.append(System.lineSeparator());
                            });


        });
        return sb.toString();
    }


    @Override
    public boolean doesUsernameExist(String username) {
        return this.userRepository.existsByUsername(username);
    }

    @Override
    public User findUserByUserName(String username) {
        return this.userRepository.findByUsername(username);
    }
}
