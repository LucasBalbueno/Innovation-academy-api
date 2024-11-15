package Innovation.Academy.Innovation_academy_api.service;

import Innovation.Academy.Innovation_academy_api.dto.AuthenticationDTO;
import Innovation.Academy.Innovation_academy_api.dto.LoginResponseDTO;
import Innovation.Academy.Innovation_academy_api.dto.RegisterDTO;
import Innovation.Academy.Innovation_academy_api.entities.UserEntity;
import Innovation.Academy.Innovation_academy_api.repository.UserRepository;
import Innovation.Academy.Innovation_academy_api.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    public ResponseEntity login(AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(),data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserEntity) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    public ResponseEntity register(RegisterDTO data){
        if(this.userRepository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();
        if(this.userRepository.findByUsername(data.username()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        UserEntity newUser= new UserEntity(data.name(),data.username(),data.email(),encryptedPassword,data.role());
        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }


}
