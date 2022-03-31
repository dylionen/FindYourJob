package com.job.findyourjob;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getHomePage(Authentication authentication, Model model) {
        if (authentication != null) {
            model.addAttribute(authentication);
        }
        model.addAttribute("welcomeMessage", "welcomeMessage");

        return "index";
    }

    @GetMapping("login")
    public String loginPage(Model model){
        return "login";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/foradmin")
    @ResponseBody
    public String someMethod() {
        return "<h1> Dla admina</h1>";
    }
}
