package com.job.findyourjob.modules.jobs;

import org.springframework.stereotype.Service;

@Service
public class JobService {
    public final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    private void createJob(Job job){
        jobRepository.save(job);
    }
}
