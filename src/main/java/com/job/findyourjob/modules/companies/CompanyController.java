package com.job.findyourjob.modules.companies;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    @GetMapping("/add")
    public String createCompany(Model model) {
        Company company = new Company();
        model.addAttribute("company", company);
        return "post-company";
    }
}
