package softuni.exam.instagraphlite.models.dto;

import softuni.exam.instagraphlite.models.entity.Post;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "posts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostSeedRootDto {
    @XmlElement(name = "post")
    List<PostSeedDto> posts;

    public List<PostSeedDto> getPosts() {
        return posts;
    }

    public void setPosts(List<PostSeedDto> posts) {
        this.posts = posts;
    }
}
