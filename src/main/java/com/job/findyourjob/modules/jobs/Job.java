package com.job.findyourjob.modules.jobs;

import com.job.findyourjob.modules.companies.Company;
import com.job.findyourjob.modules.jobs.elements.EducationExperience;
import com.job.findyourjob.modules.jobs.elements.OtherBenefits;
import com.job.findyourjob.modules.jobs.elements.Responsibility;
import com.job.findyourjob.modules.jobs.liked.Liked;
import com.job.findyourjob.modules.users.User;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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

    private Timestamp publishedOn;
    private Integer vacancy;
    private String employmentStatus;
    private String experience;
    private String salary;
    private String gender;

    private Timestamp applicationDeadline;

    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private User createdBy;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "job_id")
    private Set<Responsibility> responsibilities;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "job_id")
    private Set<EducationExperience> educationExperiences;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "job_id")
    private Set<OtherBenefits> otherBenefits;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private Set<Liked> likedSet;

    @ManyToOne
    private Company company;

    public Job(JobDTO jobDTO) {

        this.email = jobDTO.getEmail();
        this.jobTitle = jobDTO.getJobTitle();
        this.location = jobDTO.getLocation();
        this.jobRegion = jobDTO.getJobRegion();
        this.jobType = jobDTO.getJobType();
        this.jobDescription = jobDTO.getJobDescription();

        this.publishedOn = new Timestamp(Calendar.getInstance().getTimeInMillis());
        this.vacancy = jobDTO.getVacancy();
        this.employmentStatus = jobDTO.getEmploymentStatus();
        this.experience = jobDTO.getExperience();
        this.salary = jobDTO.getSalary();
        this.gender = jobDTO.getGender();
        this.active = true;
        //this.applicationDeadline = new Timestamp(new Date(jobDTO.getApplicationDeadline()).getTime());
    }
}
