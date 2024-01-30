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

    }
}
