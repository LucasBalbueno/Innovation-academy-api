package Innovation.Academy.Innovation_academy_api.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table (name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column (name = "user_id")
    private UUID userId;

    @Column(unique = true)
    private String username;

    private String password;

    private String email;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
