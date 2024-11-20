package Innovation.Academy.Innovation_academy_api.controller;

import Innovation.Academy.Innovation_academy_api.dto.EventDTO;
import Innovation.Academy.Innovation_academy_api.dto.SchedulerAddEventDTO;
import Innovation.Academy.Innovation_academy_api.dto.SchedulerCreateDTO;
import Innovation.Academy.Innovation_academy_api.dto.SchedulerDTO;
import Innovation.Academy.Innovation_academy_api.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/schedulers")
public class SchedulerController {

    @Autowired
    private SchedulerService schedulerService;

    @PostMapping
    public ResponseEntity<SchedulerDTO> createScheduler(@RequestBody SchedulerCreateDTO data){
        SchedulerDTO dto = schedulerService.createScheduler(data);
        return ResponseEntity.ok(dto);
    }


    @PostMapping("/addEventInScheduler")
    public SchedulerDTO addEventInScheduler(@RequestBody SchedulerAddEventDTO schedulerAddEventDTO){
        return schedulerService.addEventInScheduler(schedulerAddEventDTO);
    }
    @GetMapping("/getEventsScheduler/{id}")
    public List<EventDTO> getEventsScheduler(@PathVariable("id") Integer userId){
        return schedulerService.getEventsScheduler(userId);
    }
}
