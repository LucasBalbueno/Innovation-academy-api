package Innovation.Academy.Innovation_academy_api.repository;

import Innovation.Academy.Innovation_academy_api.entities.SchedulerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulerRepository extends JpaRepository<SchedulerEntity, Integer> {
}
