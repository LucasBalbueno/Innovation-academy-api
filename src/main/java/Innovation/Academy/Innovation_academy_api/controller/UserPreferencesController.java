package Innovation.Academy.Innovation_academy_api.controller;

import Innovation.Academy.Innovation_academy_api.dto.UserPreferencesDTO;
import Innovation.Academy.Innovation_academy_api.service.UserPreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users-preferences")
public class UserPreferencesController {

    @Autowired
    private UserPreferencesService userPreferencesService;

    @GetMapping
    public List<UserPreferencesDTO> getAllUserPreferences() {
        return userPreferencesService.getAllUserPreferences();
    }

    @GetMapping("/{id}")
    public UserPreferencesDTO getUserPreferencesById(@PathVariable Integer id) {
        return userPreferencesService.getUserPreferencesById(id);
    }

    @PostMapping
    public UserPreferencesDTO createUserPreferences(@RequestBody UserPreferencesDTO userPreferencesDTO) {
        return userPreferencesService.createUserPreferences(userPreferencesDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserPreferencesDTO> updateUserPreferences(@PathVariable Integer id, @RequestBody UserPreferencesDTO userPreferencesDTO) {
        UserPreferencesDTO updatedUser = userPreferencesService.updateUserPreferences(id, userPreferencesDTO);
        return updatedUser != null ? ResponseEntity.ok(updatedUser) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserPreferences(@PathVariable Integer id) {
        userPreferencesService.deleteUserPreferences(id);
        return ResponseEntity.noContent().build();
    }
}
