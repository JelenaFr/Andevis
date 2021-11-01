package com.assignment.andevis.controller;


import com.assignment.andevis.model.User;
import com.assignment.andevis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

    @Autowired
    private UserService userService;


    @RequestMapping("/")
    public String welcome() {
        return "index";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/welcome")
    public String createRegistration(User user) {
        userService.createUser(user);
        return "welcome";
    }
}
