package Innovation.Academy.Innovation_academy_api.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Integer userId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String job;
    private String dayCount;

}
