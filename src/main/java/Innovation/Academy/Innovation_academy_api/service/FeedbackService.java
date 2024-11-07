package Innovation.Academy.Innovation_academy_api.service;

import Innovation.Academy.Innovation_academy_api.dto.FeedbackDTO;
import Innovation.Academy.Innovation_academy_api.dto.UserDTO;
import Innovation.Academy.Innovation_academy_api.entities.FeedbackEntity;
import Innovation.Academy.Innovation_academy_api.entities.UserEntity;
import Innovation.Academy.Innovation_academy_api.repository.FeedbackRepository;
import Innovation.Academy.Innovation_academy_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

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

    public FeedbackDTO createFeedback(FeedbackDTO feedbackDTO){
        FeedbackEntity feedbackEntity = new FeedbackEntity();
        feedbackEntity.setFeedbackStars(feedbackEntity.getFeedbackStars());
        feedbackEntity.setFeedbackDescription(feedbackEntity.getFeedbackDescription());
        feedbackEntity.setUserId(feedbackEntity.getUserId());
        feedbackRepository.save(feedbackEntity);

        return convertToDTO(feedbackEntity);
    }

    public FeedbackDTO updateFeedback(Integer id,FeedbackDTO feedbackDTO){
        Optional<FeedbackEntity> feedbackEntityOptional = feedbackRepository.findById(id);
        if(feedbackEntityOptional.isPresent()){
            FeedbackEntity feedbackEntity = new FeedbackEntity();
            feedbackEntity.setFeedbackStars(feedbackEntity.getFeedbackStars());
            feedbackEntity.setFeedbackDescription(feedbackEntity.getFeedbackDescription());
            feedbackEntity.setUserId(feedbackEntity.getUserId());
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
        feedbackDTO.setFeedbackId(feedbackEntity.getFeedbackId());
        feedbackDTO.setFeedbackStars(feedbackEntity.getFeedbackStars());
        feedbackDTO.setFeedbackDescription(feedbackEntity.getFeedbackDescription());
        feedbackDTO.setUserId(feedbackEntity.getUserId());
        return feedbackDTO;
    }

}