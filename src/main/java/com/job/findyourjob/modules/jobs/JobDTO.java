package com.job.findyourjob.modules.jobs;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;


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

    private Integer vacancy;
    private String employmentStatus;
    private String experience;
    private String salary;
    private String gender;

    private Timestamp applicationDeadline;

    private List<String> responsibilities;
    private List<String> educationExperience;
    private List<String> otherBenefits;

}
