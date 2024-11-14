package Innovation.Academy.Innovation_academy_api.repository;

import Innovation.Academy.Innovation_academy_api.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

}
