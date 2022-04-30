package com.job.findyourjob.modules.companies;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.File;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CompanyDTO {
    private Long id;

    @NotEmpty(message = "Company name is required")
    @Size(min = 4, max = 64,message = "Company name must have from 4 to 64 digits")
    private String companyName;
    private String tagLine;
    private String companyDescription;

    private String companyWebsite;
    private String companyWebsiteFb;
    private String companyWebsiteTw;
    private String companyWebsiteLi;

    private MultipartFile logo;

    public CompanyDTO(Company company){
        this.id = company.getId();
        this.companyName = company.getCompanyName();
        this.tagLine = company.getTagLine();
        this.companyDescription = company.getCompanyDescription();
        this.companyWebsite = company.getCompanyWebsite();
        this.companyWebsiteFb = company.getCompanyWebsiteFb();
        this.companyWebsiteTw = company.getCompanyWebsiteTw();
        this.companyWebsiteLi = company.getCompanyWebsiteLi();
        //this.Logo = company.getLogo();
    }
}
