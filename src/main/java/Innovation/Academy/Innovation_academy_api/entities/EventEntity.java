package Innovation.Academy.Innovation_academy_api.entities;

import Innovation.Academy.Innovation_academy_api.enums.EventLevelEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Events")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id", nullable = false)
    private Integer eventId;

    @Column(name = "event_name", nullable = false)
    private String eventName;

//  MUDAR PARA TIPO DATA
    @Column(name = "event_date", nullable = false)
    private String eventDate;

    @Column(name = "event_description", nullable = false)
    private String eventDescription;

    @Column(name = "event_image", nullable = false)
    private String eventImage;

    @Column(name = "event_url", nullable = false)
    private String eventURL;

    @Column(name = "event_level", nullable = false)
    private EventLevelEnum eventLevel;
}
