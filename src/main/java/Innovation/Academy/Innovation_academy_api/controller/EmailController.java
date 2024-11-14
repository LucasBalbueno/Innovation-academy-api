package Innovation.Academy.Innovation_academy_api.controller;

import Innovation.Academy.Innovation_academy_api.dto.EmailRequest;
import Innovation.Academy.Innovation_academy_api.security.JwtTokenProvider;
import Innovation.Academy.Innovation_academy_api.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/send-email")
    public String sendEmail(@RequestBody EmailRequest emailRequest) {
        String email = emailRequest.getEmail();
        if (email == null || email.isEmpty()) {
            return "E-mail não pode ser nulo ou vazio!";
        }

        String token = jwtTokenProvider.generatePasswordResetToken(email);

        String resetLink = "http://localhost:5173/login/password_recovery?token=" + token;

        emailService.sendSimpleEmail(
                email,
                "Recuperação de Senha",
                "Clique no link para recuperar sua senha: " + resetLink + "\n O link de recuperação de senha expira em 5 minutos! \n Se você não fez essa solicitação, ignore esse email.");
        return "E-mail enviado com sucesso!";
    }

}