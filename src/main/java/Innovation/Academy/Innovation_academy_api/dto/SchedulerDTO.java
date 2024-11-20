package Innovation.Academy.Innovation_academy_api.dto;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class SchedulerDTO {
    private Integer schedulerId;
    private UserDTO userDTO;
    private List<EventDTO> events;
}
