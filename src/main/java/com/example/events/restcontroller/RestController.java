package com.example.events.restcontroller;

import com.example.events.DTO.ErrorDTO;
import com.example.events.DTO.EventCreateDTO;
import com.example.events.DTO.EventDTO;
import com.example.events.models.Event;
import com.example.events.models.User;
import com.example.events.service.EventService;
import com.example.events.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    final private EventService eventService;
    final private UserService userService;

    public RestController(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    @GetMapping("/api/events")
    public ResponseEntity<?> getEvents() {
        List<Event> events = eventService.getAllEvents();
        return ResponseEntity.ok(events.stream().map(EventDTO::new).collect(Collectors.toList()));
    }

    @GetMapping("/api/event/{id}")
    public ResponseEntity<?> getEventById(@PathVariable Long id) {
        if (eventService.getEventById(id) != null) {

            return ResponseEntity.ok(new EventDTO(eventService.getEventById(id)));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/api/createEvent")
    public ResponseEntity<?> createEvent(@RequestBody EventCreateDTO eventCreateDTO) {
        try {
            if (userService.getByUsername(eventCreateDTO.username()) == null) {
                return ResponseEntity.status(406).body(new ErrorDTO("User doesnt exists"));
            }
            Event event = new Event(eventCreateDTO);
            User user = userService.getByUsername(eventCreateDTO.username());
            eventService.createEventAndSetOwner(event, user);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new ErrorDTO("Failed to create event"));
        }
    }
}
