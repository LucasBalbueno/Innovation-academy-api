package Innovation.Academy.Innovation_academy_api.repository;

import Innovation.Academy.Innovation_academy_api.entities.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedbackEntity, Integer> {
    Optional<FeedbackEntity> findByFeedbackUsername(String feedbackUsername);
}
