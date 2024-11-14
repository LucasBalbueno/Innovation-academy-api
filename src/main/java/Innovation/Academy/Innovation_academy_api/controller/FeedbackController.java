package Innovation.Academy.Innovation_academy_api.controller;

import Innovation.Academy.Innovation_academy_api.dto.FeedbackDTO;
import Innovation.Academy.Innovation_academy_api.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;


    @GetMapping
    public List<FeedbackDTO> getAllFeedbacks(){
        return feedbackService.getAllFeedbacks();
    }

    @GetMapping("/user/{userId}")
    public FeedbackDTO getFeedbackById(@PathVariable Integer userId){
        return feedbackService.getFeedbackById(userId);
    }

    @PostMapping("/user/{userId}")
    public FeedbackDTO createFeedback(@PathVariable Integer userId, @RequestBody FeedbackDTO dto){
        return feedbackService.createFeedback(userId, dto);
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<FeedbackDTO> updateFeedback(@PathVariable Integer userId, @RequestBody FeedbackDTO dto){
        FeedbackDTO feedbackUpdateDTO = feedbackService.updateFeedback(userId, dto);
        return feedbackUpdateDTO != null ? ResponseEntity.ok(feedbackUpdateDTO) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Integer userId){
        feedbackService.deleteFeedback(userId);
        return ResponseEntity.noContent().build();
    }
}
