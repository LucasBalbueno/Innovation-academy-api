package Innovation.Academy.Innovation_academy_api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

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

    @Column(name = "first_name",nullable = false)
    private String firstName;

    @Column(name = "last_name",nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "number_phone")
    private String numberPhone;

    @Column(name = "job", nullable = false)
    private String job;

    @Column(name = "technologies")
    private String technologies;

    @Column(name = "biography")
    private String biography;

    @Column(name = "day_count", nullable = false)
    private String dayCount;

    @Column(name = "user_image")
    private String userImage;

    @OneToOne(mappedBy = "user")
    @PrimaryKeyJoinColumn
    private FeedbackEntity feedback;

   @JsonIgnore
   @ManyToMany
   @JoinTable(name = "Users_Courses",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "cargo_id"))
   private Set<CourseEntity> courses;

   @OneToOne(mappedBy = "user")
   @PrimaryKeyJoinColumn
   private UserPreferencesEntity userPreferences;
}