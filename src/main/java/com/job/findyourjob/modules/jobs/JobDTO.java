package com.job.findyourjob.modules.jobs;

import lombok.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JobDTO {
    private Long id;
    private String email;
    private String jobTitle;
    private String location;
    private String jobRegion;
    private String jobType;
    private String jobDescription;

    private Integer vacancy;
    private String employmentStatus;
    private String experience;
    private String salary;
    private String gender;

    private Timestamp applicationDeadline;

    private List<String> responsibilities;
    private List<String> educationExperience;
    private List<String> otherBenefits;

    private Long companyId;

    public JobDTO(Job job) {
        this.id = job.getId();
        this.email = job.getEmail();
        this.jobTitle = job.getJobTitle();
        this.location = job.getLocation();
        this.jobRegion = job.getJobRegion();
        this.jobType = job.getJobType();
        this.jobDescription = job.getJobDescription();

        this.vacancy = job.getVacancy();
        this.employmentStatus = job.getEmploymentStatus();
        this.experience = job.getExperience();
        this.salary = job.getSalary();
        this.gender = job.getGender();

        this.applicationDeadline = job.getApplicationDeadline();

        this.responsibilities = new ArrayList<>();
        job.getResponsibilities().stream().forEach(responsibility -> this.responsibilities.add(responsibility.getValue()));
        this.educationExperience = new ArrayList<>();
        job.getEducationExperiences().stream().forEach(educationExperience -> this.educationExperience.add(educationExperience.getValue()));
        this.otherBenefits = new ArrayList<>();
        job.getOtherBenefits().stream().forEach(otherBenefits -> this.otherBenefits.add(otherBenefits.getValue()));
    }

}
