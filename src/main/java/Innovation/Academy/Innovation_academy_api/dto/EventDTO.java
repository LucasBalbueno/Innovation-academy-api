package Innovation.Academy.Innovation_academy_api.dto;

import Innovation.Academy.Innovation_academy_api.enums.EventLevelEnum;
import lombok.Data;

@Data
public class EventDTO {
    private Integer eventId;
    private String eventName;
    private String eventDate;
    private String eventDescription;
    private String eventImage;
    private String eventURL;
    private EventLevelEnum eventLevel;
}