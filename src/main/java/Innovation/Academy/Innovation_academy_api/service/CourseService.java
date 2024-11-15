package Innovation.Academy.Innovation_academy_api.service;

import Innovation.Academy.Innovation_academy_api.dto.CourseDTO;
import Innovation.Academy.Innovation_academy_api.entities.CourseEntity;
import Innovation.Academy.Innovation_academy_api.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<CourseDTO> getAllCourses() {
        return courseRepository
                .findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CourseDTO getCourseById(Integer id) {
        Optional<CourseEntity> courseEntityOptional = courseRepository.findById(id);
        return courseEntityOptional.map(this::convertToDTO).orElse(null);
    }

    public CourseDTO createCourse(CourseDTO courseDTO) {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setCourseName(courseDTO.getCourseName());
        courseEntity.setCourseLevel(courseDTO.getCourseLevel());
        courseEntity.setCourseDescription(courseDTO.getCourseDescription());
        courseEntity.setCourseType(courseDTO.getCourseType());
        courseEntity.setTeacherId(courseDTO.getTeacherId());

        courseRepository.save(courseEntity);

        return convertToDTO(courseEntity);
    }

    public CourseDTO updateCourse(Integer id, CourseDTO courseDTO) {
        Optional<CourseEntity> courseEntityOptional = courseRepository.findById(id);

        if(courseEntityOptional.isPresent()) {
            CourseEntity courseEntity = courseEntityOptional.get();
            courseEntity.setCourseName(courseDTO.getCourseName());
            courseEntity.setCourseLevel(courseDTO.getCourseLevel());
            courseEntity.setCourseDescription(courseDTO.getCourseDescription());
            courseEntity.setCourseType(courseDTO.getCourseType());
            courseEntity.setTeacherId(courseDTO.getTeacherId());

            courseRepository.save(courseEntity);

            return convertToDTO(courseEntity);
        }

        return null;
    }

    public void deleteCourse(Integer id) {
        courseRepository.deleteById(id);
    }

    private CourseDTO convertToDTO(CourseEntity courseEntity) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseId(courseEntity.getCourseId());
        courseDTO.setCourseName(courseEntity.getCourseName());
        courseDTO.setCourseLevel(courseEntity.getCourseLevel());
        courseDTO.setCourseDescription(courseEntity.getCourseDescription());
        courseDTO.setCourseType(courseEntity.getCourseType());
        courseDTO.setTeacherId(courseEntity.getTeacherId());

        return courseDTO;
    }
}