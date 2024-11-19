package Innovation.Academy.Innovation_academy_api.service;

import Innovation.Academy.Innovation_academy_api.dto.UserPreferencesDTO;
import Innovation.Academy.Innovation_academy_api.entities.UserEntity;
import Innovation.Academy.Innovation_academy_api.entities.UserPreferencesEntity;
import Innovation.Academy.Innovation_academy_api.repository.UserPreferencesRepository;
import Innovation.Academy.Innovation_academy_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserPreferencesService {

    @Autowired
    private UserPreferencesRepository userPreferencesRepository;

    @Autowired
    private UserRepository userRepository;

    public List<UserPreferencesDTO> getAllUserPreferences() {
        return userPreferencesRepository
                .findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UserPreferencesDTO getUserPreferencesById(Integer userId) {
        Optional<UserPreferencesEntity> userPreferencesOptional = userPreferencesRepository.findById(userId);
        return userPreferencesOptional.map(this::convertToDTO).orElse(null);
    }

    public UserPreferencesDTO createUserPreferences(Integer userId, UserPreferencesDTO userPreferencesDTO) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);

        if (userEntityOptional.isPresent()) {
            UserPreferencesEntity userPreferencesEntity = new UserPreferencesEntity();
            userPreferencesEntity.setUser(userEntityOptional.get());
            userPreferencesEntity.setTheme(userPreferencesDTO.getTheme());
            userPreferencesEntity.setTextSize(userPreferencesDTO.getTextSize());
            userPreferencesEntity.setNotification(userPreferencesDTO.getNotification());

            userPreferencesRepository.save(userPreferencesEntity);

            return convertToDTO(userPreferencesEntity);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    public UserPreferencesDTO updateUserPreferences(Integer userId, UserPreferencesDTO userPreferencesDTO) {
        Optional<UserPreferencesEntity> userPreferencesOptional = userPreferencesRepository.findById(userId);

        if(userPreferencesOptional.isPresent()){
            UserPreferencesEntity userPreferencesEntity = userPreferencesOptional.get();
            userPreferencesEntity.setTheme(userPreferencesDTO.getTheme());
            userPreferencesEntity.setTextSize(userPreferencesDTO.getTextSize());
            userPreferencesEntity.setNotification(userPreferencesDTO.getNotification());

            userPreferencesRepository.save(userPreferencesEntity);
            return convertToDTO(userPreferencesEntity);
        }

        return null;
    }

    public UserPreferencesDTO patchUserPreferences(Integer userid, UserPreferencesDTO userPreferencesDTO) {
        Optional<UserPreferencesEntity> userPreferencesOptional = userPreferencesRepository.findById(userid);

        if (userPreferencesOptional.isPresent()) {
            UserPreferencesEntity userPreferencesEntity = userPreferencesOptional.get();

            if (userPreferencesDTO.getTheme() != null) {
                userPreferencesEntity.setTheme(userPreferencesDTO.getTheme());
            }
            if (userPreferencesDTO.getTextSize() != null) {
                userPreferencesEntity.setTextSize(userPreferencesDTO.getTextSize());
            }
            if (userPreferencesDTO.getNotification() != null) {
                userPreferencesEntity.setNotification(userPreferencesDTO.getNotification());
            }

            userPreferencesRepository.save(userPreferencesEntity);
            return convertToDTO(userPreferencesEntity);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User preferences not found");
        }
    }

    public void deleteUserPreferences(Integer userId) {
        userPreferencesRepository.deleteById(userId);
    }

    private UserPreferencesDTO convertToDTO(UserPreferencesEntity userPreferencesEntity) {
        UserPreferencesDTO userPreferencesDTO = new UserPreferencesDTO();
        userPreferencesDTO.setUserId(userPreferencesEntity.getUser().getUserId());
        userPreferencesDTO.setTheme(userPreferencesEntity.getTheme());
        userPreferencesDTO.setTextSize(userPreferencesEntity.getTextSize());
        userPreferencesDTO.setNotification(userPreferencesEntity.getNotification());

        return userPreferencesDTO;
    }

}