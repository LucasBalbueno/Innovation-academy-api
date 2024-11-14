package Innovation.Academy.Innovation_academy_api.entities;

import Innovation.Academy.Innovation_academy_api.enums.CourseTypeEnum;
import Innovation.Academy.Innovation_academy_api.enums.LevelEnum;
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
@Table(name = "Courses")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id", nullable = false)
    private Integer courseId;

    @Column(name = "course_name", nullable = false)
    private String courseName;

    @Column(name = "course_level", nullable = false)
    private LevelEnum courseLevel;

    @Column(name = "course_description")
    private String courseDescription;

    @Column(name = "course_type")
    private CourseTypeEnum courseType;

    @ManyToMany(mappedBy = "courses")
    private List<UserEntity> users;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "teacher_id")
    private TeacherEntity teacher;
}
