package Innovation.Academy.Innovation_academy_api.service;

import Innovation.Academy.Innovation_academy_api.dto.CourseDTO;
import Innovation.Academy.Innovation_academy_api.dto.UserDTO;
import Innovation.Academy.Innovation_academy_api.entities.CourseEntity;
import Innovation.Academy.Innovation_academy_api.entities.UserEntity;
import Innovation.Academy.Innovation_academy_api.repository.CourseRepository;
import Innovation.Academy.Innovation_academy_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<UserDTO> getAllUsers(){
        return userRepository
                .findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Integer id){
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        return userEntityOptional.map(this::convertToDTO).orElse(null);
    }

    public UserDTO getUserByEmail(String email) {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            return convertToDTO(userEntity);
        }
        return null;
    }

    public UserDTO createUser(UserDTO userDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
        userEntity.setName(userDTO.getName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setNumberPhone(userDTO.getNumberPhone());
        userEntity.setJob(userDTO.getJob());
        userEntity.setTechnologies(userDTO.getTechnologies());
        userEntity.setBiography(userDTO.getBiography());
        userEntity.setDayCount(userDTO.getDayCount());
        userEntity.setUserImage(userDTO.getUserImage());

        if (userEntity.getCourses() == null) {
            userEntity.setCourses(new ArrayList<>());
        }

        userRepository.save(userEntity);

        return convertToDTO(userEntity);
    }

    public UserDTO updateUser(Integer id,UserDTO userDTO){
        Optional<UserEntity> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            UserEntity userEntity = userOptional.get();
            userEntity.setUsername(userDTO.getUsername());
            userEntity.setPassword(userDTO.getPassword());
            userEntity.setName(userDTO.getName());
            userEntity.setEmail(userDTO.getEmail());
            userEntity.setNumberPhone(userDTO.getNumberPhone());
            userEntity.setJob(userDTO.getJob());
            userEntity.setTechnologies(userDTO.getTechnologies());
            userEntity.setBiography(userDTO.getBiography());
            userEntity.setDayCount(userDTO.getDayCount());
            userEntity.setUserImage(userDTO.getUserImage());

            userRepository.save(userEntity);

            return convertToDTO(userEntity);
        }
        return null;
    }

    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }

    private UserDTO convertToDTO(UserEntity userEntity){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userEntity.getUserId());
        userDTO.setUsername(userEntity.getUsername());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setName(userEntity.getName());
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
}