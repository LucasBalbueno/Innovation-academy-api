package Innovation.Academy.Innovation_academy_api.controller;

import Innovation.Academy.Innovation_academy_api.dto.CourseDTO;
import Innovation.Academy.Innovation_academy_api.dto.CourseDTOCreate;
import Innovation.Academy.Innovation_academy_api.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<CourseDTO> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public CourseDTO getCourseById(@PathVariable Integer id) {
        return courseService.getCourseById(id);
    }

    @PostMapping
    public CourseDTOCreate createCourse(@RequestBody CourseDTOCreate courseDTO) {
        return courseService.createCourse(courseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTOCreate> updateCourse(@PathVariable Integer id, @RequestBody CourseDTOCreate courseDTOCreate) {
        CourseDTOCreate updatedCourse = courseService.updateCourse(id, courseDTOCreate);
        return updatedCourse != null ? ResponseEntity.ok(updatedCourse) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Integer id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/registerUser/{idUser}/course/{idCourse}")
    public CourseDTO registerUserInCourse(@PathVariable Integer idUser,@PathVariable Integer idCourse){
        return courseService.registerUserInCourse(idUser,idCourse);
    }
}