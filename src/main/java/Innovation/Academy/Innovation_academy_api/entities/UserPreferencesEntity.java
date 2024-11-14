package Innovation.Academy.Innovation_academy_api.entities;

import Innovation.Academy.Innovation_academy_api.enums.NotificationEnum;
import Innovation.Academy.Innovation_academy_api.enums.TextSizeEnum;
import Innovation.Academy.Innovation_academy_api.enums.ThemeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "UserPreferences")
public class UserPreferencesEntity {

    @Id
    @Column (name = "user_id")
    private Integer userId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    @Column(name = "theme", nullable = false)
    private ThemeEnum theme;

    @Enumerated(EnumType.STRING)
    @Column(name = "text_size", nullable = false)
    private TextSizeEnum textSize;

    @Enumerated(EnumType.STRING)
    @Column(name = "notification", nullable = false)
    private NotificationEnum notification;
}