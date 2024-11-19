package Innovation.Academy.Innovation_academy_api.dto;

import Innovation.Academy.Innovation_academy_api.enums.LevelEnum;
import lombok.Data;

import java.util.Date;

@Data
public class EventDTO {
    private Integer eventId;
    private String eventName;
    private Date eventDateStart;
    private Date eventDateEnd;
    private String eventDescription;
    private String eventImage;
    private String eventURL;
    private LevelEnum eventLevel;
}