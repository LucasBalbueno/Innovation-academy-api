package Innovation.Academy.Innovation_academy_api.security;

import Innovation.Academy.Innovation_academy_api.entities.UserEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.securirty.token.secret}")
    private String secret;

    public String generateToken(UserEntity user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                    .withIssuer("innovationacademyapi")
                    .withSubject(user.getEmail())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
            return token;
        }catch (JWTCreationException exception){
            throw new RuntimeException("Erro while generating tokne", exception);
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("innovationacademyapi")
                    .build()
                    .verify(token)
                    .getSubject();

        }catch (JWTVerificationException exception){
            return "";
        }
    }

    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
