package Innovation.Academy.Innovation_academy_api.service;

import Innovation.Academy.Innovation_academy_api.dto.CourseDTO;
import Innovation.Academy.Innovation_academy_api.dto.CourseDTOCreate;
import Innovation.Academy.Innovation_academy_api.dto.UserDTO;
import Innovation.Academy.Innovation_academy_api.entities.CourseEntity;
import Innovation.Academy.Innovation_academy_api.entities.UserEntity;
import Innovation.Academy.Innovation_academy_api.repository.CourseRepository;
import Innovation.Academy.Innovation_academy_api.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private static final Logger log = LoggerFactory.getLogger(CourseService.class);
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;

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

    public CourseDTOCreate createCourse(CourseDTOCreate courseDTO) {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setCourseName(courseDTO.getCourseName());
        courseEntity.setCourseLevel(courseDTO.getCourseLevel());
        courseEntity.setCourseDescription(courseDTO.getCourseDescription());
        courseEntity.setCourseType(courseDTO.getCourseType());


        courseRepository.save(courseEntity);

        return convertToDTOCreate(courseEntity);
    }

    public CourseDTOCreate updateCourse(Integer id, CourseDTOCreate courseDTO) {
        Optional<CourseEntity> courseEntityOptional = courseRepository.findById(id);

        if(courseEntityOptional.isPresent()) {
            CourseEntity courseEntity = courseEntityOptional.get();
            courseEntity.setCourseName(courseDTO.getCourseName());
            courseEntity.setCourseLevel(courseDTO.getCourseLevel());
            courseEntity.setCourseDescription(courseDTO.getCourseDescription());
            courseEntity.setCourseType(courseDTO.getCourseType());

            courseRepository.save(courseEntity);

            return convertToDTOCreate(courseEntity);
        }

        return null;
    }

    public CourseDTO registerUserInCourse(Integer idUser, Integer idCourse){
        Optional<CourseEntity> optionalCourse = courseRepository.findById(idCourse);
        Optional<UserEntity> optionalUser = userRepository.findById(idUser);
        if(optionalCourse.isPresent()){
            if (optionalUser.isPresent()){
                CourseEntity courseEntity = optionalCourse.get();

                courseEntity.getUsers().add(optionalUser.get());

                //aqui da  erro
                courseRepository.save(courseEntity);

                return convertToDTO(courseEntity);
            }else {
                throw new RuntimeException("User is not found!");
            }

        }else{
            throw new RuntimeException("Course is not found!");
        }

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

        Set<UserEntity> listUsers = courseEntity.getUsers();
        Set<UserDTO> listUsersDTO = listUsers.stream()
                        .map(user -> convertUsersToDTO(user))
                        .collect(Collectors.toSet());

        courseDTO.setUsers(listUsersDTO);

        return courseDTO;
    }

    private CourseDTOCreate convertToDTOCreate(CourseEntity courseEntity) {
        CourseDTOCreate courseDTO = new CourseDTOCreate();
        courseDTO.setCourseId(courseEntity.getCourseId());
        courseDTO.setCourseName(courseEntity.getCourseName());
        courseDTO.setCourseLevel(courseEntity.getCourseLevel());
        courseDTO.setCourseDescription(courseEntity.getCourseDescription());
        courseDTO.setCourseType(courseEntity.getCourseType());

        return courseDTO;
    }

    private UserDTO convertUsersToDTO(UserEntity userEntity){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userEntity.getUserId());
        userDTO.setUsername(userEntity.getUsername());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setFirstName(userEntity.getFirstName());
        userDTO.setLastName(userEntity.getLastName());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setNumberPhone(userEntity.getNumberPhone());
        userDTO.setJob(userEntity.getJob());
        userDTO.setTechnologies(userEntity.getTechnologies());
        userDTO.setBiography(userEntity.getBiography());
        userDTO.setDayCount(userEntity.getDayCount());
        userDTO.setUserImage(userEntity.getUserImage());
        return userDTO;
    }

    private UserEntity convertUserToEntity(UserDTO userDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userDTO.getUserId());
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setNumberPhone(userDTO.getNumberPhone());
        userEntity.setJob(userDTO.getJob());
        userEntity.setTechnologies(userDTO.getTechnologies());
        userEntity.setBiography(userDTO.getBiography());
        userEntity.setDayCount(userDTO.getDayCount());
        userEntity.setUserImage(userDTO.getUserImage());
        return userEntity;
    }
}