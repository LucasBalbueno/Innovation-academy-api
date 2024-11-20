package Innovation.Academy.Innovation_academy_api.service;

import Innovation.Academy.Innovation_academy_api.dto.*;
import Innovation.Academy.Innovation_academy_api.entities.CourseEntity;
import Innovation.Academy.Innovation_academy_api.entities.EventEntity;
import Innovation.Academy.Innovation_academy_api.entities.SchedulerEntity;
import Innovation.Academy.Innovation_academy_api.entities.UserEntity;
import Innovation.Academy.Innovation_academy_api.repository.SchedulerRepository;
import Innovation.Academy.Innovation_academy_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SchedulerService {
    @Autowired
    private SchedulerRepository schedulerRepository;
    @Autowired
    private UserRepository userRepository;

    public SchedulerDTO createScheduler(SchedulerCreateDTO data){
        Optional<SchedulerEntity> schedulerEntityOptional = schedulerRepository.findById(data.getIdUser());
        if(schedulerEntityOptional.isPresent()){
            return null;
        }
        Optional<UserEntity> userEntityOptional = userRepository.findById(data.getIdUser());
        if(userEntityOptional.isPresent()){
            SchedulerEntity schedulerEntity = new SchedulerEntity();
            schedulerEntity.setSchedulerId(userEntityOptional.get().getUserId());
            schedulerEntity.setUser(userEntityOptional.get());
            List<EventEntity> listEventEntity = new ArrayList<>();
            schedulerEntity.setEvents(listEventEntity);

            schedulerRepository.save(schedulerEntity);

            return convertSchedulerToDTO(schedulerEntity);
        }
        return null;

    }

    public List<EventDTO> getEventsScheduler(Integer userId){
        Optional<SchedulerEntity> schedulerEntityOptional = schedulerRepository.findById(userId);
        if(schedulerEntityOptional.isPresent()){
            SchedulerEntity schedulerEntity = schedulerEntityOptional.get();
            return (schedulerEntity.getEvents()
                .stream()
                .map(this::convertEventToDTO)
                .collect(Collectors.toList()));
        }

        return null;
    }

    public SchedulerDTO addEventInScheduler (SchedulerAddEventDTO dto){
        Optional<SchedulerEntity> scheduler = schedulerRepository.findById(dto.getUserId());

        if(scheduler.isPresent()){
            SchedulerEntity schedulerEntity = scheduler.get();
            EventDTO eventDTO = createEventDTO(dto);
            schedulerEntity.getEvents().add(convertEventToEntity(eventDTO));
            schedulerRepository.save(schedulerEntity);
            return convertSchedulerToDTO(schedulerEntity);
        }
        return null;
    }

    private EventDTO createEventDTO(SchedulerAddEventDTO schedulerAddEventDTO){
        EventDTO eventDTO = new EventDTO();
        eventDTO.setEventId(schedulerAddEventDTO.getEventId());
        eventDTO.setEventName(schedulerAddEventDTO.getEventName());
        eventDTO.setEventDateStart(schedulerAddEventDTO.getEventDateStart());
        eventDTO.setEventDateEnd(schedulerAddEventDTO.getEventDateEnd());
        eventDTO.setEventDescription(schedulerAddEventDTO.getEventDescription());
        eventDTO.setEventImage(schedulerAddEventDTO.getEventImage());
        eventDTO.setEventURL(schedulerAddEventDTO.getEventURL());
        eventDTO.setEventLevel(schedulerAddEventDTO.getEventLevel());
        return eventDTO;
    }


    private EventDTO convertEventToDTO(EventEntity eventEntity) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setEventId(eventEntity.getEventId());
        eventDTO.setEventName(eventEntity.getEventName());
        eventDTO.setEventDateStart(eventEntity.getEventDateStart());
        eventDTO.setEventDateEnd(eventEntity.getEventDateEnd());
        eventDTO.setEventDescription(eventEntity.getEventDescription());
        eventDTO.setEventImage(eventEntity.getEventImage());
        eventDTO.setEventURL(eventEntity.getEventURL());
        eventDTO.setEventLevel(eventEntity.getEventLevel());

        return eventDTO;
    }

    private EventEntity convertEventToEntity(EventDTO eventDTO) {
        EventEntity eventEntity = new EventEntity();
        eventEntity.setEventId(eventDTO.getEventId());
        eventEntity.setEventName(eventDTO.getEventName());
        eventEntity.setEventDateStart(eventDTO.getEventDateStart());
        eventEntity.setEventDateEnd(eventDTO.getEventDateEnd());
        eventEntity.setEventDescription(eventDTO.getEventDescription());
        eventEntity.setEventImage(eventDTO.getEventImage());
        eventEntity.setEventURL(eventDTO.getEventURL());
        eventEntity.setEventLevel(eventDTO.getEventLevel());

        return eventEntity;
    }

    private SchedulerDTO convertSchedulerToDTO(SchedulerEntity schedulerEntity) {
        SchedulerDTO schedulerDTO = new SchedulerDTO();
        schedulerDTO.setSchedulerId(schedulerEntity.getSchedulerId());
        schedulerDTO.setUserDTO(convertUserToDTO(schedulerEntity.getUser()));
        schedulerDTO.setEvents(schedulerEntity
                .getEvents()
                .stream()
                .map(event->convertEventToDTO(event))
                .collect(Collectors.toList()));
        return schedulerDTO;
    }

    private UserDTO convertUserToDTO(UserEntity userEntity){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userEntity.getUserId());
        userDTO.setUsername(userEntity.getUsername());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setName(userEntity.getName());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setNumberPhone(userEntity.getNumberPhone());
        userDTO.setJob(userEntity.getJob());
        userDTO.setTechnologies(userEntity.getTechnologies());
        userDTO.setBiography(userEntity.getBiography());
        userDTO.setDayCount(userEntity.getDayCount());
        userDTO.setUserImage(userEntity.getUserImage());

        userDTO.setCourses(userEntity.getCourses().stream()
                .map(this::convertToCourseDTO)
                .collect(Collectors.toList()));

        return userDTO;
    }

    private CourseDTO convertToCourseDTO(CourseEntity courseEntity) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseId(courseEntity.getCourseId());
        courseDTO.setCourseName(courseEntity.getCourseName());
        courseDTO.setCourseLevel(courseEntity.getCourseLevel());
        courseDTO.setCourseDescription(courseEntity.getCourseDescription());
        courseDTO.setCourseType(courseEntity.getCourseType());

        return courseDTO;
    }
}
