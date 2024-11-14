package Innovation.Academy.Innovation_academy_api.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private Integer userId;
    private String username;
    private String password;
    private String name;
    private String email;
    private String numberPhone;
    private String job;
    private String technologies;
    private String biography;
    private String dayCount;
    private String userImage;
    private List<CourseDTO> courses;
}
