package com.example.events.service;

import com.example.events.models.Event;
import com.example.events.models.User;
import com.example.events.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final UserService userService;

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
            return eventRepository.findById(id).get().getUsers_db().size();
        } catch (Exception e) {
            return 0;
        }
    }

    public void joinEvent(Long eventId, String username) {
        try {
            Event event = eventRepository.findById(eventId).orElse(new Event());
            User user = userService.getByUsername(username);
            if (event.getUsers_db().contains(user)) {
                return;
            }

            event.addParticipant(user);
            eventRepository.save(event);
        } catch (Exception e) {
            System.out.println("failed add user to event as participant");
        }

    }

    public String getEventOwnerName(Long id) {
        try {
            return userService.findById( eventRepository.findById(id).get().getCreatorId()).getName();
        } catch (Exception e) {
            return null;
        }
    }
}
