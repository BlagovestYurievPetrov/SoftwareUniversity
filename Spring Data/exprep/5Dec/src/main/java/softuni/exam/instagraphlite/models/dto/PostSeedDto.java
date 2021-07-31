package softuni.exam.instagraphlite.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PostSeedDto {
    @XmlElement(name = "caption")
    private String caption;
    @XmlElement(name = "user")
    private UsernameDto usernameDto;
    @XmlElement(name = "picture")
    private PictureDto pictureDto;

    @NotBlank
    @Size(min = 21)
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public UsernameDto getUsernameDto() {
        return usernameDto;
    }

    public void setUsernameDto(UsernameDto usernameDto) {
        this.usernameDto = usernameDto;
    }

    public PictureDto getPictureDto() {
        return pictureDto;
    }

    public void setPictureDto(PictureDto pictureDto) {
        this.pictureDto = pictureDto;
    }
}
