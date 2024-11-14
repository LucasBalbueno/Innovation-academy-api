package Innovation.Academy.Innovation_academy_api.service;

import Innovation.Academy.Innovation_academy_api.dto.CourseDTO;
import Innovation.Academy.Innovation_academy_api.dto.UserDTO;
import Innovation.Academy.Innovation_academy_api.entities.CourseEntity;
import Innovation.Academy.Innovation_academy_api.entities.UserEntity;
import Innovation.Academy.Innovation_academy_api.repository.CourseRepository;
import Innovation.Academy.Innovation_academy_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    public ResponseEntity<List<CourseDTO>> getCoursesByUser(Integer userId) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);

        if (userEntityOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<CourseDTO> courseDTOs =userEntityOptional.get().getCourses().stream()
                .map(this::convertToCourseDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(courseDTOs);
    }

    public ResponseEntity<List<UserDTO>> getUsersByCourse(Integer courseId) {
        Optional<CourseEntity> courseEntityOptional = courseRepository.findById(courseId);

        if (courseEntityOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<UserDTO> userDTOs = courseEntityOptional.get().getUsers().stream()
                .map(this::convertToUserDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(userDTOs);
    }

    public ResponseEntity<UserDTO> enrollUserInCourse(Integer userId, Integer courseId) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        Optional<CourseEntity> courseEntityOptional = courseRepository.findById(courseId);

        if (userEntityOptional.isEmpty() && courseEntityOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        UserEntity userEntity = userEntityOptional.get();
        CourseEntity courseEntity = courseEntityOptional.get();

        userEntity.getCourses().add(courseEntity);
        userRepository.save(userEntity);

        return ResponseEntity.ok(convertToDTO(userEntity));
    }

    public ResponseEntity<UserDTO> unenrollUserFromCourse(Integer userId, Integer courseId) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        Optional<CourseEntity> courseEntityOptional = courseRepository.findById(courseId);

        if (userEntityOptional.isEmpty() && courseEntityOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        UserEntity userEntity = userEntityOptional.get();
        CourseEntity courseEntity = courseEntityOptional.get();

        userEntity.getCourses().remove(courseEntity);
        userRepository.save(userEntity);

        return ResponseEntity.ok(convertToDTO(userEntity));
    }

    private UserDTO convertToDTO(UserEntity userEntity){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userEntity.getUserId());
        userDTO.setUsername(userEntity.getUsername());
        userDTO.setPassword(userEntity.getPassword());
        userEntity.setName(userDTO.getName());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setNumberPhone(userEntity.getNumberPhone());
        userDTO.setJob(userEntity.getJob());
        userDTO.setTechnologies(userEntity.getTechnologies());
        userDTO.setBiography(userEntity.getBiography());
        userDTO.setDayCount(userEntity.getDayCount());
        userDTO.setUserImage(userEntity.getUserImage());

        userDTO.setCourses(userEntity.getCourses().stream()
                .map(this::convertToCourseDTO)
                .collect(Collectors.toList()));

        return userDTO;
    }

    private CourseDTO convertToCourseDTO(CourseEntity courseEntity) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseId(courseEntity.getCourseId());
        courseDTO.setCourseName(courseEntity.getCourseName());
        courseDTO.setCourseLevel(courseEntity.getCourseLevel());
        courseDTO.setCourseDescription(courseEntity.getCourseDescription());
        courseDTO.setCourseType(courseEntity.getCourseType());

        return courseDTO;
    }

    private UserDTO convertToUserDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userEntity.getUserId());
        userDTO.setUsername(userEntity.getUsername());
        userDTO.setPassword(userEntity.getPassword());
        userEntity.setName(userDTO.getName());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setNumberPhone(userEntity.getNumberPhone());
        userDTO.setJob(userEntity.getJob());
        userDTO.setTechnologies(userEntity.getTechnologies());
        userDTO.setBiography(userEntity.getBiography());
        userDTO.setDayCount(userEntity.getDayCount());
        userDTO.setUserImage(userEntity.getUserImage());

        return userDTO;
    }
}
