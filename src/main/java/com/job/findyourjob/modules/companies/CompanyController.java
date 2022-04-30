package com.job.findyourjob.modules.companies;

import com.job.findyourjob.configurations.SecurityConfiguration;
import com.job.findyourjob.modules.users.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
    public String postCompany(@Valid CompanyDTO companyDTO, @RequestParam("logo") MultipartFile logo, BindingResult bindingResult, Model model, Principal principal) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("company", companyDTO);
            return "post-company";
        } else {
            Company company = new Company(companyDTO);
            company.setCreatedBy(userService.getUserByUserName(principal.getName()));


            try {
                String fileName = StringUtils.cleanPath(logo.getOriginalFilename());
                System.out.println(fileName);
                String millis = String.valueOf(System.currentTimeMillis());
                Path path = Paths.get(SecurityConfiguration.UPLOAD_DIR + "/" + millis+ "/" + fileName);

                File directory = new File(SecurityConfiguration.UPLOAD_DIR + "/" + millis);
                if (!directory.exists()) {
                    directory.mkdir();
                }

                Files.copy(logo.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                company.setLogo(path.toAbsolutePath().toString());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            companyService.createNewCompany(company);
            return "redirect:/user";
        }
    }
}
