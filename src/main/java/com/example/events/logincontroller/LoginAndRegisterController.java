package com.example.events.logincontroller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@AllArgsConstructor
public class LoginAndRegisterController {
    @GetMapping("/login")
    public String login(Model m, @RequestParam() Map<String, String> param) {
        m.addAttribute("param", param);
        if (param.containsKey("error")) {
            m.addAttribute("error", "Error: Invalid credentials.");
        }
        return "login";
    }
}
