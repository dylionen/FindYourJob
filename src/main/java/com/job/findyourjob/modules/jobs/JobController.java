package com.job.findyourjob.modules.jobs;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/job")
public class JobController {

    @GetMapping("/add")
    public String newJob(Model model) {
        JobDTO jobDTO = new JobDTO();
        model.addAttribute("jobDTO", jobDTO);
        return "post-job";
    }

    @PostMapping("/add")
    public String createJob(JobDTO jobDTO, Model model, Principal principal) {
        System.out.println(jobDTO);

        return "post-job";

    }
}
