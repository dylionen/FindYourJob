package com.job.findyourjob;

import com.job.findyourjob.modules.users.UserRegistrationDTO;
import com.job.findyourjob.modules.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String getHomePage(Authentication authentication, Model model) {
        if (authentication != null) {
            model.addAttribute(authentication);
        }
        model.addAttribute("welcomeMessage", "welcomeMessage");

        return "index";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        return "login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        UserRegistrationDTO user = new UserRegistrationDTO();
        model.addAttribute("user", user);
        return "signup";
    }

    @PostMapping("/process_register")
    public String registerConfirnation(@Valid @ModelAttribute("user") UserRegistrationDTO user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            return "signup";

        } else {
            userService.createNewUser(user);
            return "index";
        }
    }

/*
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/foradmin")
    @ResponseBody
    public String someMethod() {
        return "<h1> Dla admina</h1>";
    }*/
}
