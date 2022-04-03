package com.job.findyourjob.modules.companies;

import com.job.findyourjob.modules.jobs.Job;
import com.job.findyourjob.modules.users.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private String tagLine;
    private String companyDescription;

    private String companyWebsite;
    private String companyWebsiteFb;
    private String companyWebsiteTw;
    private String companyWebsiteLi;

    private String Logo;

    private Timestamp createdDate;


    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private User createdBy;

    @OneToMany(mappedBy = "company")
    private Set<Job> jobs;

    public User getCreatedBy() {
        return createdBy;
    }
}
