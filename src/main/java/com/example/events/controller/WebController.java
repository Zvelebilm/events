package com.example.events.controller;

import com.example.events.models.User;
import com.example.events.security.SecurityService;
import com.example.events.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.NoSuchElementException;

@Controller
@AllArgsConstructor
public class WebController {
    private SecurityService securityService;
    private UserService userService;

    @GetMapping("/")
    public String index(Model model) {
        try {

            User loggedInUser = securityService.getLoggedInUser();
            model.addAttribute("loggedInUser", loggedInUser);

        } catch (NoSuchElementException e) {
            return "redirect:/login";
        }
        return "index";
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
