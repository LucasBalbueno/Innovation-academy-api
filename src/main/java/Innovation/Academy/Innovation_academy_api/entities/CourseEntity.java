package Innovation.Academy.Innovation_academy_api.entities;

import Innovation.Academy.Innovation_academy_api.enums.CourseTypeEnum;
import Innovation.Academy.Innovation_academy_api.enums.LevelEnum;
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

    @ManyToOne
    @JoinColumn(name = "teacher_id", insertable = false, updatable = false)
    private TeacherEntity teacher;

    @Column(name = "teacher_id", nullable = false)
    private Integer teacherId;
}
