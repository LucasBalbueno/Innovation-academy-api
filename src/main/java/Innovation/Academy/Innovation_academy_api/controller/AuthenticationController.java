package Innovation.Academy.Innovation_academy_api.controller;

import Innovation.Academy.Innovation_academy_api.dto.AuthenticationDTO;
import Innovation.Academy.Innovation_academy_api.dto.RegisterDTO;
import Innovation.Academy.Innovation_academy_api.service.AuthenticationService;
import jakarta.validation.Valid;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        return authenticationService.login(data);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        return authenticationService.register(data);
    }

}
