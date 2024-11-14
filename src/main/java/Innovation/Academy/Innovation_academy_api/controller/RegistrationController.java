package Innovation.Academy.Innovation_academy_api.controller;

import Innovation.Academy.Innovation_academy_api.dto.CourseDTO;
import Innovation.Academy.Innovation_academy_api.dto.UserDTO;
import Innovation.Academy.Innovation_academy_api.service.CourseService;
import Innovation.Academy.Innovation_academy_api.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/user/{userId}/courses")
    public ResponseEntity<List<CourseDTO>> getCoursesByUser(@PathVariable Integer userId) {
        return registrationService.getCoursesByUser(userId);
    }

    @GetMapping("/course/{courseId}/users")
    public ResponseEntity<List<UserDTO>> getUsersByCourse(@PathVariable Integer courseId) {
        return registrationService.getUsersByCourse(courseId);
    }

    @PostMapping("/user/{userId}/course/{courseId}")
    public ResponseEntity<UserDTO> enrollUserInCourse(@PathVariable Integer userId, @PathVariable Integer courseId) {
        return registrationService.enrollUserInCourse(userId, courseId);
    }

    @DeleteMapping("/user/{userId}/course/{courseId}")
    public ResponseEntity<UserDTO> unenrollUserFromCourse(@PathVariable Integer userId, @PathVariable Integer courseId) {
        return registrationService.unenrollUserFromCourse(userId, courseId);
    }
}
