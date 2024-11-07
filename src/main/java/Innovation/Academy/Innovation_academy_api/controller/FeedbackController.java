package Innovation.Academy.Innovation_academy_api.controller;

import Innovation.Academy.Innovation_academy_api.dto.FeedbackDTO;
import Innovation.Academy.Innovation_academy_api.service.FeedbackService;
import Innovation.Academy.Innovation_academy_api.service.UserService;
import org.apache.catalina.connector.Response;
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

    @GetMapping("/{id}")
    public FeedbackDTO getFeedbackById(@PathVariable Integer id){
        return feedbackService.getFeedbackById(id);
    }

    @PostMapping
    public FeedbackDTO createFeedback(@RequestBody FeedbackDTO dto){
        return feedbackService.createFeedback(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedbackDTO> updateFeedback(@PathVariable Integer id, @RequestBody FeedbackDTO dto){
        FeedbackDTO feedbackUpdateDTO = feedbackService.updateFeedback(id, dto);
        return feedbackUpdateDTO != null ? ResponseEntity.ok(feedbackUpdateDTO) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Integer id){
        feedbackService.deleteFeedback(id);
        return ResponseEntity.noContent().build();
    }
}
