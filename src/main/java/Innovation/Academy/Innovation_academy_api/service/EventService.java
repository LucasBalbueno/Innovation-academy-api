package Innovation.Academy.Innovation_academy_api.service;

import Innovation.Academy.Innovation_academy_api.dto.EventDTO;
import Innovation.Academy.Innovation_academy_api.entities.EventEntity;
import Innovation.Academy.Innovation_academy_api.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<EventDTO> getAllEvents() {
        return eventRepository
                .findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EventDTO getEventById(Integer id) {
        Optional<EventEntity> eventEntityOptional = eventRepository.findById(id);
        return eventEntityOptional.map(this::convertToDTO).orElse(null);
    }

    public EventDTO createEvent(EventDTO eventDTO) {
        EventEntity eventEntity = new EventEntity();
        eventEntity.setEventName(eventDTO.getEventName());
        eventEntity.setEventDateStart(eventDTO.getEventDateStart());
        eventEntity.setEventDateEnd(eventDTO.getEventDateEnd());
        eventEntity.setEventDescription(eventDTO.getEventDescription());
        eventEntity.setEventImage(eventDTO.getEventImage());
        eventEntity.setEventURL(eventDTO.getEventURL());
        eventEntity.setEventLevel(eventDTO.getEventLevel());

        eventRepository.save(eventEntity);

        return convertToDTO(eventEntity);
    }

    public EventDTO updateEvent(Integer id, EventDTO eventDTO) {
        Optional<EventEntity> eventEntityOptional = eventRepository.findById(id);

        if(eventEntityOptional.isPresent()) {
            EventEntity eventEntity = eventEntityOptional.get();
            eventEntity.setEventName(eventDTO.getEventName());
            eventEntity.setEventDateStart(eventDTO.getEventDateStart());
            eventEntity.setEventDateEnd(eventDTO.getEventDateEnd());
            eventEntity.setEventDescription(eventDTO.getEventDescription());
            eventEntity.setEventImage(eventDTO.getEventImage());
            eventEntity.setEventURL(eventDTO.getEventURL());
            eventEntity.setEventLevel(eventDTO.getEventLevel());

            eventRepository.save(eventEntity);

            return convertToDTO(eventEntity);
        }

        return null;
    }

    public void deleteEvent(Integer id) {
        eventRepository.deleteById(id);
    }

    private EventDTO convertToDTO(EventEntity eventEntity) {
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
}