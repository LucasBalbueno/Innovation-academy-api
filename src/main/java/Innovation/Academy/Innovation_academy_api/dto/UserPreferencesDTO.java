package Innovation.Academy.Innovation_academy_api.dto;

import Innovation.Academy.Innovation_academy_api.enums.NotificationEnum;
import Innovation.Academy.Innovation_academy_api.enums.TextSizeEnum;
import Innovation.Academy.Innovation_academy_api.enums.ThemeEnum;
import lombok.Data;

@Data
public class UserPreferencesDTO {
    private Integer preferenceId;
    private ThemeEnum theme;
    private TextSizeEnum textSize;
    private NotificationEnum notification;
}
