package com.example.events.service;

import com.example.events.models.Event;
import com.example.events.models.User;
import com.example.events.repositories.EventRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
@NoArgsConstructor
public class EventService {
    private EventRepository eventRepository;
    private UserService userService;
    @Autowired
    public EventService(EventRepository eventRepository, UserService userService) {
        this.eventRepository = eventRepository;
        this.userService = userService;
    }
    //todo delete event
    //todo update event


    public void createEvent(Event event1) { //todo remove
        eventRepository.save(event1);
    }

    public void createEventAndSetOwner(Event event, User user) {
        event.setOwner(user);
        eventRepository.save(event);
    }

    public Event getEventById(long l) {
        return eventRepository.findById(l).orElse(null); //todo remove orElse(null)
    }

    public void saveEvent(Event event1) {
        eventRepository.save(event1);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public int getNumberOfParticipants(Long id) {
        try {
            return eventRepository.findById(id).get().getUsers().size();
        } catch (Exception e) {
            return 0;
        }
    }

    public void joinEvent(Long eventId, String username) {
        try {
            Event event = eventRepository.findById(eventId).orElse(new Event());
            User user = userService.getByUsername(username); //todo cant find user that is the problem
            event.addParticipant(user);
            eventRepository.save(event);
        } catch (Exception e) {
            System.out.println("failed add user to event as participant");
        }

    }
}
