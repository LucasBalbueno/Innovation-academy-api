package Innovation.Academy.Innovation_academy_api.service;

import Innovation.Academy.Innovation_academy_api.dto.FeedbackDTO;
import Innovation.Academy.Innovation_academy_api.entities.FeedbackEntity;
import Innovation.Academy.Innovation_academy_api.entities.UserEntity;
import Innovation.Academy.Innovation_academy_api.repository.FeedbackRepository;
import Innovation.Academy.Innovation_academy_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UserRepository userRepository;

    public List<FeedbackDTO> getAllFeedbacks() {
        return feedbackRepository
                .findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public FeedbackDTO getFeedbackById(Integer id) {
        Optional<FeedbackEntity> feedbackEntityOptional = feedbackRepository.findById(id);
        return feedbackEntityOptional.map(this::convertToDTO).orElse(null);
    }

    public FeedbackDTO createFeedback(FeedbackDTO feedbackDTO) {
        FeedbackEntity feedbackEntity = new FeedbackEntity();
        feedbackEntity.setFeedbackStars(feedbackDTO.getFeedbackStars());
        feedbackEntity.setFeedbackDescription(feedbackDTO.getFeedbackDescription());

        // Buscar o UserEntity pelo userId
        Optional<UserEntity> userEntityOptional = userRepository.findById(feedbackDTO.getUserId());
        if (userEntityOptional.isPresent()) {
            feedbackEntity.setUser(userEntityOptional.get());
        } else {
            throw new RuntimeException("User not found with ID: " + feedbackDTO.getUserId());
        }

        feedbackRepository.save(feedbackEntity);
        return convertToDTO(feedbackEntity);
    }

    public FeedbackDTO updateFeedback(Integer id, FeedbackDTO feedbackDTO) {
        Optional<FeedbackEntity> feedbackEntityOptional = feedbackRepository.findById(id);
        if (feedbackEntityOptional.isPresent()) {
            FeedbackEntity feedbackEntity = feedbackEntityOptional.get();
            feedbackEntity.setFeedbackStars(feedbackDTO.getFeedbackStars());
            feedbackEntity.setFeedbackDescription(feedbackDTO.getFeedbackDescription());

            // Atualizar o usuário se necessário
            Optional<UserEntity> userEntityOptional = userRepository.findById(feedbackDTO.getUserId());
            if (userEntityOptional.isPresent()) {
                feedbackEntity.setUser(userEntityOptional.get());
            } else {
                throw new RuntimeException("User not found with ID: " + feedbackDTO.getUserId());
            }

            feedbackRepository.save(feedbackEntity);
            return convertToDTO(feedbackEntity);
        }
        return null;
    }

    public void deleteFeedback(Integer id) {
        feedbackRepository.deleteById(id);
    }

    private FeedbackDTO convertToDTO(FeedbackEntity feedbackEntity) {
        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.setFeedbackId(feedbackEntity.getFeedbackId());
        feedbackDTO.setFeedbackStars(feedbackEntity.getFeedbackStars());
        feedbackDTO.setFeedbackDescription(feedbackEntity.getFeedbackDescription());

        // Configurar apenas o userId no DTO
        feedbackDTO.setUserId(feedbackEntity.getUser().getUserId());
        return feedbackDTO;
    }
}
