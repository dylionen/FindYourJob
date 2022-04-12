package com.job.findyourjob.modules.jobs;

import com.job.findyourjob.modules.jobs.elements.EducationExperience;
import com.job.findyourjob.modules.jobs.elements.OtherBenefits;
import com.job.findyourjob.modules.jobs.elements.Responsibility;
import com.job.findyourjob.modules.users.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.*;

@Service
public class JobService {
    public final JobRepository jobRepository;
    public final UserRepository userRepository;

    public JobService(JobRepository jobRepository, UserRepository userRepository) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    void createJob(Job job) {
        jobRepository.save(job);
    }

    @Transactional
    void createJob(JobDTO jobDTO, Principal principal) {
        Job job = new Job(jobDTO);
        job.setCreatedBy(userRepository.getUserByUserName(principal.getName()));
        Set<EducationExperience> educationExperiences = new HashSet<>();
        Set<OtherBenefits> otherBenefits = new HashSet<>();
        Set<Responsibility> responsibilities = new HashSet<>();

        jobDTO.getEducationExperience()
                .stream()
                .filter(Objects::nonNull)
                .forEach(s -> educationExperiences.add(new EducationExperience(s)));

        jobDTO.getOtherBenefits()
                .stream()
                .filter(Objects::nonNull)
                .forEach(s -> otherBenefits.add(new OtherBenefits(s)));

        jobDTO.getResponsibilities()
                .stream()
                .filter(Objects::nonNull)
                .forEach(s -> responsibilities.add(new Responsibility(s)));


        job.setResponsibilities(responsibilities);
        job.setEducationExperiences(educationExperiences);
        job.setOtherBenefits(otherBenefits);

        jobRepository.save(job);
    }
    public List<JobDTO> getJobsByUserName(String userName){
        List<JobDTO> jobDTOS = new ArrayList<>();
        List<Job> jobList = jobRepository.getJobsByCreatedBy(userRepository.getUserByUserName(userName));
        jobList.forEach(job -> jobDTOS.add(new JobDTO(job)));
        return jobDTOS;
    }
}
