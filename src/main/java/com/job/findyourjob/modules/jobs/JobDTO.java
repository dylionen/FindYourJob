package com.job.findyourjob.modules.jobs;

import com.job.findyourjob.modules.companies.Company;
import com.job.findyourjob.modules.jobs.elements.Responsibility;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JobDTO {

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


    private List<String> responsibilities;

    private String educationExperience;
    private String otherBenifits;

}
