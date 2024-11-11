package Innovation.Academy.Innovation_academy_api.service;

import Innovation.Academy.Innovation_academy_api.dto.UserPreferencesDTO;
import Innovation.Academy.Innovation_academy_api.entities.UserPreferencesEntity;
import Innovation.Academy.Innovation_academy_api.repository.UserPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserPreferencesService {

    @Autowired
    private UserPreferencesRepository userPreferencesRepository;

    public List<UserPreferencesDTO> getAllUserPreferences() {
        return userPreferencesRepository
                .findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UserPreferencesDTO getUserPreferencesById(Integer id) {
        Optional<UserPreferencesEntity> userPreferencesOptional = userPreferencesRepository.findById(id);
        return userPreferencesOptional.map(this::convertToDTO).orElse(null);
    }

    public UserPreferencesDTO createUserPreferences(UserPreferencesDTO userPreferencesDTO) {
        UserPreferencesEntity userPreferencesEntity = new UserPreferencesEntity();
        userPreferencesEntity.setTheme(userPreferencesDTO.getTheme());
        userPreferencesEntity.setTextSize(userPreferencesDTO.getTextSize());
        userPreferencesEntity.setNotification(userPreferencesDTO.getNotification());

        userPreferencesRepository.save(userPreferencesEntity);

        return convertToDTO(userPreferencesEntity);
    }

    public UserPreferencesDTO updateUserPreferences(Integer id, UserPreferencesDTO userPreferencesDTO) {
        Optional<UserPreferencesEntity> userPreferencesOptional = userPreferencesRepository.findById(id);

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

    public void deleteUserPreferences(Integer id) {
        userPreferencesRepository.deleteById(id);
    }

    private UserPreferencesDTO convertToDTO(UserPreferencesEntity userPreferencesEntity) {
        UserPreferencesDTO userPreferencesDTO = new UserPreferencesDTO();
        userPreferencesDTO.setPreferenceId(userPreferencesEntity.getPreferenceId());
        userPreferencesDTO.setTheme(userPreferencesEntity.getTheme());
        userPreferencesDTO.setTextSize(userPreferencesEntity.getTextSize());
        userPreferencesDTO.setNotification(userPreferencesEntity.getNotification());

        return userPreferencesDTO;
    }
}