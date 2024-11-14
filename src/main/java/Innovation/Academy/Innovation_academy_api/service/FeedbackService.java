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

    @Autowired
    private UserRepository userRepository;

    public List<FeedbackDTO> getAllFeedbacks(){
        return feedbackRepository
                .findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public FeedbackDTO getFeedbackById(Integer id){
        Optional<FeedbackEntity> feedbackEntityOptional = feedbackRepository.findById(id);
        return feedbackEntityOptional.map(this::convertToDTO).orElse(null);
    }

    public FeedbackDTO createFeedback(Integer userId, FeedbackDTO feedbackDTO){
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);

        if (userEntityOptional.isPresent()) {
        FeedbackEntity feedbackEntity = new FeedbackEntity();
        feedbackEntity.setUser(userEntityOptional.get());
        feedbackEntity.setFeedbackStars(feedbackDTO.getFeedbackStars());
        feedbackEntity.setFeedbackDescription(feedbackDTO.getFeedbackDescription());

        feedbackRepository.save(feedbackEntity);

        return convertToDTO(feedbackEntity);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    public FeedbackDTO updateFeedback(Integer id,FeedbackDTO feedbackDTO){
        Optional<FeedbackEntity> feedbackEntityOptional = feedbackRepository.findById(id);

        if(feedbackEntityOptional.isPresent()){
            FeedbackEntity feedbackEntity = feedbackEntityOptional.get();
            feedbackEntity.setFeedbackStars(feedbackDTO.getFeedbackStars());
            feedbackEntity.setFeedbackDescription(feedbackDTO.getFeedbackDescription());

            feedbackRepository.save(feedbackEntity);
            return convertToDTO(feedbackEntity);
        }

        return null;
    }

    public void deleteFeedback(Integer id){
        feedbackRepository.deleteById(id);
    }

    private FeedbackDTO convertToDTO(FeedbackEntity feedbackEntity){
        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.setUserId(feedbackEntity.getUser().getUserId());
        feedbackDTO.setFeedbackStars(feedbackEntity.getFeedbackStars());
        feedbackDTO.setFeedbackDescription(feedbackEntity.getFeedbackDescription());

        return feedbackDTO;
    }
}