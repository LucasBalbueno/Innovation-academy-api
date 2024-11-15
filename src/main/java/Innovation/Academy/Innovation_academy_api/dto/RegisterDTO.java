package Innovation.Academy.Innovation_academy_api.dto;

import Innovation.Academy.Innovation_academy_api.enums.UserRole;

public record RegisterDTO(String name, String username, String email, String password, UserRole role) {
}
