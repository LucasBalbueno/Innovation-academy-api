package Innovation.Academy.Innovation_academy_api.service;

import Innovation.Academy.Innovation_academy_api.entities.UserEntity;
import Innovation.Academy.Innovation_academy_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);
        UserEntity userEntity = userOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com email: " + email));

        // Verifique se a senha está presente (pode ser null se o usuário for criado de outra forma)
        if (userEntity.getPassword() == null) {
            throw new BadCredentialsException("User has no password");
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(email) // Ou userEntity.getEmail(), se preferir
                .password(userEntity.getPassword()) // Senha criptografada do banco
                .roles("USER") // Ou authorities("USER") se estiver usando Spring Security anterior a 5.7
                .build();
    }

public UserDetails loadUserById(Integer id) throws UsernameNotFoundException {
    Optional<UserEntity> userOptional = userRepository.findById(id);

    if (userOptional.isEmpty()) {
        throw new UsernameNotFoundException("Usuário não encontrado com id: " + id);
    }

    UserEntity user = userOptional.get();

    return org.springframework.security.core.userdetails.User
            .withUsername(user.getEmail())
            .password(user.getPassword())
            .build();
}


}