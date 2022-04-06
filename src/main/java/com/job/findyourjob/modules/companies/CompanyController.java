package com.job.findyourjob.modules.companies;

import com.job.findyourjob.modules.users.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService companyService;
    private final UserService userService;

    public CompanyController(CompanyService companyService, UserService userService) {
        this.companyService = companyService;
        this.userService = userService;
    }


    @GetMapping("/add")
    public String createCompany(Model model) {
        CompanyDTO company = new CompanyDTO();
        model.addAttribute("company", company);
        return "post-company";
    }

    @PostMapping("/add")
    public String postCompany(@Valid CompanyDTO companyDTO, BindingResult bindingResult, Model model, Principal principal) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("company", companyDTO);
            return "post-company";
        } else {
            Company company = new Company(companyDTO);
            company.setCreatedBy(userService.getUserByUserName(principal.getName()));
            companyService.createNewCompany(company);
            return "redirect:/user";
        }
    }
}
