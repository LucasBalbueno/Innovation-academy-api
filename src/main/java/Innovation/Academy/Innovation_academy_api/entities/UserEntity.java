package Innovation.Academy.Innovation_academy_api.entities;

import Innovation.Academy.Innovation_academy_api.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "Users")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "user_id",nullable = false)
    private Integer userId;

    @Column(name = "username", nullable = false,unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "number_phone")
    private String numberPhone;

    @Column(name = "job")
    private String job;

    @Column(name = "technologies")
    private String technologies;

    @Column(name = "biography")
    private String biography;

    @Column(name = "day_count")
    private String dayCount;

    @Column(name = "user_image")
    private String userImage;

    @Column(name = "user_role")
    private UserRole role;

    @OneToOne(mappedBy = "user")
    @PrimaryKeyJoinColumn
    private FeedbackEntity feedback;

    @ManyToMany
    @JoinTable(
            name = "users_courses",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<CourseEntity> courses;

    @OneToOne(mappedBy = "user")
    @PrimaryKeyJoinColumn
    private UserPreferencesEntity userPreferences;

    public UserEntity(String name, String username, String email, String password, UserRole role) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
    
    @Override
    public String getUsername(){
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}