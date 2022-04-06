package com.job.findyourjob.modules.companies;

import com.job.findyourjob.modules.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    List<Company> findAll();

    List<Company> getCompaniesByCreatedBy(User user);
}
