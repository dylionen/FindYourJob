package com.job.findyourjob.modules.jobs;

import com.job.findyourjob.modules.companies.CompanyDTO;
import com.job.findyourjob.modules.companies.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/job")
public class JobController {

    private final CompanyService companyService;
    private final JobService jobService;

    public JobController(CompanyService companyService, JobService jobService) {
        this.companyService = companyService;
        this.jobService = jobService;
    }

    @GetMapping("/add")
    public String newJob(Model model,Principal principal) {
        JobDTO jobDTO = new JobDTO();
        model.addAttribute("jobDTO", jobDTO);

        List<CompanyDTO> companyDTOList = companyService.getCompaniesByUserName(principal.getName());
        model.addAttribute("companies",companyDTOList);
        return "post-job";
    }

    @PostMapping("/add")
    public String createJob(JobDTO jobDTO, Model model, Principal principal) {
        System.out.println(jobDTO);
        jobService.createJob(jobDTO,principal);
        return "redirect:/user";

    }
}
