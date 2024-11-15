package Innovation.Academy.Innovation_academy_api.repository;

import Innovation.Academy.Innovation_academy_api.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserDetails findByEmail(String email);
    UserDetails findByUsername(String username);
}
