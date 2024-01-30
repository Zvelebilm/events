package com.example.events.WebLoginController;

import com.example.events.models.Event;
import com.example.events.models.User;
import com.example.events.security.SecurityService;
import com.example.events.service.EventService;
import com.example.events.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Controller
@AllArgsConstructor
public class WebController {
    private SecurityService securityService;
    private UserService userService;
    private EventService eventService;

    @GetMapping("/")
    public String index(Model model) {
        try {
            if (securityService.getLoggedInUser() == null) {
                return "redirect:/login";
            } else {
                User loggedInUser = securityService.getLoggedInUser();
                model.addAttribute("loggedInUser", loggedInUser.getName()); //todo swap to DTO
                model.addAttribute("events", eventService.getAllEvents());
            }
        } catch (NoSuchElementException e) {
            return "redirect:/login";
        }

        return "index";
    }

    @GetMapping("/event/{id}")
    public String event(@PathVariable Long id, Model model) {
        try {
            if (securityService.getLoggedInUser() == null) {
                return "redirect:/login";
            } else {
                User loggedInUser = securityService.getLoggedInUser();
                model.addAttribute("loggedInUser", loggedInUser.getName());
                model.addAttribute("event", eventService.getEventById(id));
                model.addAttribute("numberOfParticipants", eventService.getNumberOfParticipants(id));
                model.addAttribute("eventCreator", eventService.getEventOwnerName(id));
            }
        } catch (NoSuchElementException e) {
            return "redirect:/login";
        }
        return "event";
    }

    @PostMapping("/joinevent")
    public String joinEvent(Long eventId) {
        eventService.joinEvent(eventId, securityService.getLoggedInUser().getName());
        return "redirect:/event/" + eventId;
    }

    @GetMapping("/createEvent")
    public String createEvent() {
        return "createEvent";
    }

    @PostMapping("/createEvent")
    public String createEvent(@ModelAttribute Event event) {
        User user = securityService.getLoggedInUser();
        eventService.createEventAndSetOwner(event, user);
        return "redirect:/";
    }

    @GetMapping("/api")
    public String api() {
        return "api";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(String username, String password) {
        userService.creatNewUser(username, password);
        return "redirect:/login";
    }
}
