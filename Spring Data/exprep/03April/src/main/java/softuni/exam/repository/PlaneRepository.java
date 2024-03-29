package softuni.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entities.Plane;

@Repository
public interface PlaneRepository extends JpaRepository<Plane,Long>{

    boolean existsByRegisterNumber(String registerNumber);

    Plane findByRegisterNumber(String registerNumber);

}
