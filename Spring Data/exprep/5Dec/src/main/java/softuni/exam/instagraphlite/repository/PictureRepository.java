package softuni.exam.instagraphlite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.instagraphlite.models.entity.Picture;

import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Integer> {
    boolean existsByPath(String path);
    Picture findPictureByPath(String path);
    List<Picture> findAllBySizeGreaterThanOrderBySize(double size);
}
