package com.example.events.restcontroller;

import com.example.events.service.EventService;
import com.example.events.service.UserService;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    final private EventService eventService;
    final private UserService userService;

    public RestController(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }



}
