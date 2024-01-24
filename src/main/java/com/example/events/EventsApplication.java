package com.example.events;

import com.example.events.models.Event;
import com.example.events.models.User;
import com.example.events.service.EventService;
import com.example.events.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
@NoArgsConstructor
@AllArgsConstructor
public class EventsApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(EventsApplication.class, args);
    }
    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;

    @Override
    public void run(String... args) throws Exception {


        userService.saveUser(new User("user1", "password1"));
        userService.saveUser(new User("user2", "password2"));
        userService.saveUser(new User("user3", "password3"));
        userService.saveUser(new User("user4", "password4"));
        userService.saveUser(new User("user5", "password5"));

        Event event1 = new Event("event1", new Date(), "location1", 10, 10, "description1", "image1", "category1");

        event1.setOwner(userService.getByUsername("user1"));
        eventService.creatNewEvent(event1);
        eventService.saveEvent(event1);
       // user2.addToEvents(eventService.getEventById(1L));
        userService.saveUser(userService.getByUsername("user1"));
        eventService.saveEvent(event1);


    }
}
