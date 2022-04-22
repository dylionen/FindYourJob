package com.job.findyourjob.modules.jobs;

import com.job.findyourjob.modules.companies.CompanyDTO;
import com.job.findyourjob.modules.companies.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public String newJob(Model model, Principal principal) {
        JobDTO jobDTO = new JobDTO();
        model.addAttribute("jobDTO", jobDTO);

        List<CompanyDTO> companyDTOList = companyService.getCompaniesByUserName(principal.getName());
        model.addAttribute("companies", companyDTOList);
        return "post-job";
    }

    @PostMapping("/add")
    public String createJob(@Valid @ModelAttribute("jobDTO") JobDTO jobDTO, BindingResult bindingResult, Model model, Principal principal) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("jobDTO", jobDTO);
            List<CompanyDTO> companyDTOList = companyService.getCompaniesByUserName(principal.getName());
            model.addAttribute("companies", companyDTOList);
            return "post-job";
        }
        jobService.createJob(jobDTO, principal);
        return "redirect:/user";

    }

    @GetMapping("/{id}")
    public String getCurrentJob(@PathVariable Long id, Model model, Principal principal) {
        Job job = jobService.getJobById(id);
        //System.out.println(job);
        if (job == null) {
            throw new RuntimeException("Job offer not exists");
        }
        model.addAttribute("job", job);
        return "job-single";
    }

}
