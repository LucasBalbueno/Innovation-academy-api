package Innovation.Academy.Innovation_academy_api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "Users")
public class UserEntity {

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

    @ManyToMany
    @JoinTable(
            name = "Users_Courses",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<CourseEntity> courses;

    @OneToOne(mappedBy = "user")
    private UserPreferencesEntity userPreferences;

    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private SchedulerEntity scheduler;
}