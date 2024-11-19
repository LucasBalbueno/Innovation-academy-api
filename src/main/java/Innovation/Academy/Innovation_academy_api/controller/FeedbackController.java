package Innovation.Academy.Innovation_academy_api.controller;

import Innovation.Academy.Innovation_academy_api.entities.FeedbackEntity;
import Innovation.Academy.Innovation_academy_api.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<?> createFeedback(@RequestBody FeedbackEntity feedback) {
        try {
            FeedbackEntity createdFeedback = feedbackService.createFeedback(feedback);
            return new ResponseEntity<>(createdFeedback, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<FeedbackEntity>> getAllFeedbacks() {
        List<FeedbackEntity> feedbacks = feedbackService.getAllFeedbacks();
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackEntity> getFeedbackById(@PathVariable Integer id) {
        Optional<FeedbackEntity> feedback = feedbackService.getFeedbackById(id);
        if (feedback.isPresent()) {
            return new ResponseEntity<>(feedback.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedbackEntity> updateFeedback(@PathVariable Integer id, @RequestBody FeedbackEntity feedback) {
        FeedbackEntity updatedFeedback = feedbackService.updateFeedback(id, feedback);
        if (updatedFeedback != null) {
            return new ResponseEntity<>(updatedFeedback, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Integer id) {
        if (feedbackService.deleteFeedback(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
