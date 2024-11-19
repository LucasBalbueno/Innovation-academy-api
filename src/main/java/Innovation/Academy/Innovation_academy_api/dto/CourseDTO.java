package Innovation.Academy.Innovation_academy_api.dto;

import Innovation.Academy.Innovation_academy_api.enums.CourseTypeEnum;
import Innovation.Academy.Innovation_academy_api.enums.LevelEnum;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class CourseDTO {
    private Integer courseId;
    private String courseName;
    private LevelEnum courseLevel;
    private String courseDescription;
    private CourseTypeEnum courseType;
    private Integer teacherId;
}
