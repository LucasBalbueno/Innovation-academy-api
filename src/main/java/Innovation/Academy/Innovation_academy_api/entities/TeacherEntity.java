package Innovation.Academy.Innovation_academy_api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Teachers")
public class TeacherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id", nullable = false)
    private Integer teacherId;

    @Column(name = "teacher_name", nullable = false)
    private String teacherName;

    @Column(name = "teacher_biography")
    private String teacherBiography;

    @Column(name = "teacher_image")
    private String teacherImage;

    @OneToMany(mappedBy = "teacher")
    private List<CourseEntity> courses;
}
