package com.job.findyourjob.modules.jobs;

import com.job.findyourjob.modules.companies.CompanyDTO;
import com.job.findyourjob.modules.companies.CompanyService;
import com.job.findyourjob.modules.jobs.liked.Liked;
import com.job.findyourjob.modules.jobs.liked.LikedRepository;
import com.job.findyourjob.modules.users.User;
import com.job.findyourjob.modules.users.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/job")
public class JobController {

    private final CompanyService companyService;
    private final JobService jobService;
    private final LikedRepository likedRepository;
    private final UserService userService;

    public JobController(CompanyService companyService, JobService jobService, LikedRepository likedRepository, UserService userService) {
        this.companyService = companyService;
        this.jobService = jobService;
        this.likedRepository = likedRepository;
        this.userService = userService;
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
        User user = userService.getUserByUserName(principal.getName());
        //System.out.println(job);
        if (job == null) {
            throw new RuntimeException("Job offer not exists");
        }

        Liked liked = likedRepository.getLikedByUserAndJob(user, job);
        System.out.println(liked);
        Boolean likedByUser = liked != null;

        model.addAttribute("job", job);
        model.addAttribute("liked", liked);
        return "job-single";
    }

}
