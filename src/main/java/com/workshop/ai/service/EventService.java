package com.workshop.ai.service;

import com.workshop.ai.dto.EventDTO;
import com.workshop.ai.entity.Event;
import com.workshop.ai.entity.EventType;
import com.workshop.ai.entity.Product;
import com.workshop.ai.entity.User;
import com.workshop.ai.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventService {
    private EventRepository eventRepository;
    private UserService userService;
    private ProductService productService;

    public EventService(EventRepository eventRepository, UserService userService, ProductService productService) {
        this.eventRepository = eventRepository;
        this.userService = userService;
        this.productService = productService;
    }

    public EventDTO saveEvent(EventDTO event) {
        final Event savedEvent = eventRepository.save(mapToEntity(event));
        return mapToDTO(savedEvent);
    }

    public EventDTO getEventById(String id) {
        final Event event = eventRepository.findById(UUID.fromString(id)).orElse(null);
        return event != null ? mapToDTO(event) : null;
    }

    public List<EventDTO> getEvents() {
        return eventRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public void deleteEvent(String id) {
        eventRepository.deleteById(UUID.fromString(id));
    }

    protected List<EventDTO> getEventsByUserId(String userId) {
        return eventRepository.findByUserId(UUID.fromString(userId))
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    private EventDTO mapToDTO(Event event) {
        return new EventDTO(
                event.getId().toString(),
                event.getUser().getId().toString(),
                event.getProduct().getId().toString(),
                event.getEventType().toString()
        );
    }

    private Event mapToEntity(EventDTO eventDTO) {
        final User user = userService.findUserById(eventDTO.userId());
        final Product product = productService.findProductById(eventDTO.productId());
        final EventType eventType = EventType.valueOf(eventDTO.eventType());

        return new Event(
                eventType,
                user,
                product
        );
    }
}
