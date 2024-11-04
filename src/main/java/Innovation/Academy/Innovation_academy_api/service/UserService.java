package Innovation.Academy.Innovation_academy_api.service;

import Innovation.Academy.Innovation_academy_api.dto.UserDTO;
import Innovation.Academy.Innovation_academy_api.entities.UserEntity;
import Innovation.Academy.Innovation_academy_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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

    public UserDTO createUser(UserDTO userDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setJob(userDTO.getJob());
        userEntity.setDayCount(userDTO.getDayCount());

        userRepository.save(userEntity);

        return convertToDTO(userEntity);
    }

    public UserDTO updateUser(Integer id,UserDTO userDTO){
        Optional<UserEntity> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            UserEntity userEntity = userOptional.get();
            userEntity.setUsername(userDTO.getUsername());
            userEntity.setPassword(userDTO.getPassword());
            userEntity.setFirstName(userDTO.getFirstName());
            userEntity.setLastName(userDTO.getLastName());
            userEntity.setEmail(userDTO.getEmail());
            userEntity.setJob(userDTO.getJob());
            userEntity.setDayCount(userDTO.getDayCount());

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
        userDTO.setFirstName(userEntity.getFirstName());
        userDTO.setLastName(userEntity.getLastName());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setJob(userEntity.getJob());
        userDTO.setDayCount(userEntity.getDayCount());
        return userDTO;
    }
}
