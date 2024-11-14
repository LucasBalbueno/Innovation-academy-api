package Innovation.Academy.Innovation_academy_api.dto;

import Innovation.Academy.Innovation_academy_api.enums.CourseTypeEnum;
import Innovation.Academy.Innovation_academy_api.enums.LevelEnum;
import lombok.Data;

@Data
public class CourseDTOCreate {
    private Integer courseId;
    private String courseName;
    private LevelEnum courseLevel;
    private String courseDescription;
    private CourseTypeEnum courseType;
}
