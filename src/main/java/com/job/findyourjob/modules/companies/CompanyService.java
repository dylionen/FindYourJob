package com.job.findyourjob.modules.companies;

import com.job.findyourjob.modules.users.User;
import com.job.findyourjob.modules.users.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final UserService userService;


    public CompanyService(CompanyRepository companyRepository, UserService userService) {
        this.companyRepository = companyRepository;
        this.userService = userService;
    }

    @Transactional
    public void createNewCompany(Company company) {
        companyRepository.save(company);
    }

    public List<CompanyDTO> getCompaniesByUserName(String userName) {
        User user = userService.getUserByUserName(userName);

        //Long user = userService.getUserIdByUserName(userName);
        List<CompanyDTO> list = new ArrayList<>();
        companyRepository.getCompaniesByCreatedBy(user).stream().forEach(company -> list.add(new CompanyDTO(company)));

        list.stream().forEach(company -> System.out.println(company));

        return list;
    }

    public Company getCompanyById(Long id) {
        return companyRepository.getById(id);
    }
}
