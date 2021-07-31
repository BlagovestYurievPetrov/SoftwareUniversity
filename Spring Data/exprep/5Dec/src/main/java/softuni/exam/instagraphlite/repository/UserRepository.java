package softuni.exam.instagraphlite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.instagraphlite.models.entity.User;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByUsername(String username);

    User findByUsername(String username);

    @Query("SELECT u FROM User u ORDER BY size(u.posts) DESC , u.id")
    Set<User> allUsersWithPosts();
}
