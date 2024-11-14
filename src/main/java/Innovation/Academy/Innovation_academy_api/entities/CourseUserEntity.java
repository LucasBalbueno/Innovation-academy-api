package Innovation.Academy.Innovation_academy_api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Courses_Users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseUserEntity {

    @EmbeddedId
    private CourseUserId id;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private UserEntity user;


}
