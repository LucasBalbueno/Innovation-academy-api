package Innovation.Academy.Innovation_academy_api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class CourseUserId {
    @Column(name = "course_id")
    private Integer courseId;
    @Column(name = "user_id")
    private Integer userId;
}
