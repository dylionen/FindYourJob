package com.job.findyourjob.modules.users;

import com.job.findyourjob.modules.companies.CompanyDTO;
import com.job.findyourjob.modules.companies.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    CompanyService companyService;

    @GetMapping("/")
    public String getHomePage(Authentication authentication, Model model) {
        if (authentication != null) {
            model.addAttribute(authentication);
        }
        model.addAttribute("welcomeMessage", "welcomeMessage");

        return "index";
    }

    @GetMapping("/user")
    public String userPanel(Model model, Principal principal) {
        List<CompanyDTO> companyDTOList = companyService.getCompaniesByUserName(principal.getName());
        model.addAttribute("companies",companyDTOList);
        return "userpanel";
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
        if (userService.existsByUserName(user.getUserName()))
            bindingResult.rejectValue("userName", null, "Username exists!!");

        if (userService.existsByEmail(user.getMailAddress()))
            bindingResult.rejectValue("mailAddress", null, "Email exists!!");

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
