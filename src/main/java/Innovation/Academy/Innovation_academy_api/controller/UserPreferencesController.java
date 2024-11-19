package Innovation.Academy.Innovation_academy_api.controller;

import Innovation.Academy.Innovation_academy_api.dto.UserPreferencesDTO;
import Innovation.Academy.Innovation_academy_api.service.UserPreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/preferences")
public class UserPreferencesController {

    @Autowired
    private UserPreferencesService userPreferencesService;

    @GetMapping
    public List<UserPreferencesDTO> getAllUserPreferences() {
        return userPreferencesService.getAllUserPreferences();
    }

    @GetMapping("/user/{userid}")
    public UserPreferencesDTO getUserPreferencesById(@PathVariable Integer userid) {
        return userPreferencesService.getUserPreferencesById(userid);
    }

    @PostMapping("/user/{userId}")
    public UserPreferencesDTO createUserPreferences(@PathVariable Integer userId, @RequestBody UserPreferencesDTO userPreferencesDTO) {
        return userPreferencesService.createUserPreferences(userId, userPreferencesDTO);
    }

    @PutMapping("/user/{userid}")
    public ResponseEntity<UserPreferencesDTO> updateUserPreferences(@PathVariable Integer userid, @RequestBody UserPreferencesDTO userPreferencesDTO) {
        UserPreferencesDTO updatedUser = userPreferencesService.updateUserPreferences(userid, userPreferencesDTO);
        return updatedUser != null ? ResponseEntity.ok(updatedUser) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/user/{userid}")
    public ResponseEntity<UserPreferencesDTO> patchUserPreferences(@PathVariable Integer userid, @RequestBody UserPreferencesDTO userPreferencesDTO) {
        UserPreferencesDTO updatedUser = userPreferencesService.patchUserPreferences(userid, userPreferencesDTO);
        return updatedUser != null ? ResponseEntity.ok(updatedUser) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/user/{userid}")
    public ResponseEntity<Void> deleteUserPreferences(@PathVariable Integer userid) {
        userPreferencesService.deleteUserPreferences(userid);
        return ResponseEntity.noContent().build();
    }
}
