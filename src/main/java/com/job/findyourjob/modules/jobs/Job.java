package com.job.findyourjob.modules.jobs;

import com.job.findyourjob.modules.companies.Company;

import javax.persistence.*;

@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String jobTitle;
    private String location;
    private String jobRegion;
    private String jobType;
    private String jobDescription;

    @ManyToOne
    private Company company;
}
