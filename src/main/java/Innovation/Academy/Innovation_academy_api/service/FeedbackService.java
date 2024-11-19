package Innovation.Academy.Innovation_academy_api.service;

import Innovation.Academy.Innovation_academy_api.dto.FeedbackDTO;
import Innovation.Academy.Innovation_academy_api.entities.FeedbackEntity;
import Innovation.Academy.Innovation_academy_api.entities.UserEntity;
import Innovation.Academy.Innovation_academy_api.repository.FeedbackRepository;
import Innovation.Academy.Innovation_academy_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public FeedbackEntity createFeedback(FeedbackEntity feedback) {
        Optional<FeedbackEntity> existingFeedback = feedbackRepository.findByFeedbackUsername(feedback.getFeedbackUsername());
        if (existingFeedback.isPresent()) {
            throw new IllegalArgumentException("Usuário já enviou um feedback.");
        }
        return feedbackRepository.save(feedback);
    }

    public List<FeedbackEntity> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    public Optional<FeedbackEntity> getFeedbackById(Integer id) {
        return feedbackRepository.findById(id);
    }

    public FeedbackEntity updateFeedback(Integer id, FeedbackEntity feedback) {
        if (feedbackRepository.existsById(id)) {
            feedback.setFeedback_id(id);
            return feedbackRepository.save(feedback);
        }
        return null;
    }

    public boolean deleteFeedback(Integer id) {
        if (feedbackRepository.existsById(id)) {
            feedbackRepository.deleteById(id);
            return true;
        }
        return false;
    }
}