package com.job.findyourjob.modules.companies;

import com.job.findyourjob.modules.jobs.Job;
import com.job.findyourjob.modules.users.User;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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

    public Company(CompanyDTO companyDTO) {

        this.companyName = companyDTO.getCompanyName();
        this.tagLine = companyDTO.getTagLine();
        this.companyDescription = companyDTO.getCompanyDescription();

        this.companyWebsite = companyDTO.getCompanyWebsite();
        this.companyWebsiteFb = companyDTO.getCompanyWebsiteFb();
        this.companyWebsiteTw = companyDTO.getCompanyWebsiteTw();
        this.companyWebsiteLi = companyDTO.getCompanyWebsiteLi();

        this.Logo = companyDTO.getLogo();

        this.createdDate = new Timestamp(Calendar.getInstance().getTimeInMillis());

    }
}
