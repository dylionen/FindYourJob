package com.job.findyourjob.modules.jobs;

import antlr.StringUtils;
import com.job.findyourjob.modules.companies.CompanyRepository;
import com.job.findyourjob.modules.jobs.elements.EducationExperience;
import com.job.findyourjob.modules.jobs.elements.OtherBenefits;
import com.job.findyourjob.modules.jobs.elements.Responsibility;
import com.job.findyourjob.modules.jobs.liked.Liked;
import com.job.findyourjob.modules.jobs.liked.LikedRepository;
import com.job.findyourjob.modules.users.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;

@Service
public class JobService {
    public final JobRepository jobRepository;
    public final UserRepository userRepository;
    public final CompanyRepository companyRepository;

    public final LikedRepository likedRepository;

    public JobService(JobRepository jobRepository, UserRepository userRepository, CompanyRepository companyRepository, LikedRepository likedRepository) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.likedRepository = likedRepository;
    }

    @Transactional
    void createJob(Job job) {
        jobRepository.save(job);
    }

    @Transactional
    void createJob(JobDTO jobDTO, Principal principal) {

        Job job = new Job(jobDTO);
        job.setCreatedBy(userRepository.getUserByUserName(principal.getName()));
        Set<EducationExperience> educationExperiences = new HashSet<>();
        Set<OtherBenefits> otherBenefits = new HashSet<>();
        Set<Responsibility> responsibilities = new HashSet<>();

        jobDTO.getEducationExperience()
                .stream()
                .filter(Objects::nonNull).filter(Predicate.not(String::isEmpty))
                .forEach(s -> educationExperiences.add(new EducationExperience(s)));

        jobDTO.getOtherBenefits()
                .stream()
                .filter(Objects::nonNull).filter(Predicate.not(String::isEmpty))
                .forEach(s -> otherBenefits.add(new OtherBenefits(s)));

        jobDTO.getResponsibilities()
                .stream()
                .filter(Objects::nonNull).filter(Predicate.not(String::isEmpty))
                .forEach(s -> responsibilities.add(new Responsibility(s)));


        job.setResponsibilities(responsibilities);
        job.setEducationExperiences(educationExperiences);
        job.setOtherBenefits(otherBenefits);

        job.setCompany(companyRepository.getById(jobDTO.getCompanyId()));

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fd = formatter.parse(jobDTO.getApplicationDeadline());
            job.setApplicationDeadline(new Timestamp(fd.getTime()));
        } catch (ParseException e) {
           // throw new RuntimeException(e);
        }
        jobRepository.save(job);
    }

    public List<JobDTO> getJobsByUserName(String userName) {
        List<JobDTO> jobDTOS = new ArrayList<>();
        List<Job> jobList = jobRepository.getJobsByCreatedBy(userRepository.getUserByUserName(userName));
        jobList.forEach(job -> jobDTOS.add(new JobDTO(job)));
        return jobDTOS;
    }

    public Job getJobById(Long id) {
        return jobRepository.getById(id);
    }

    public Optional<Job> getOptionalJobByID(Long id) {
        return jobRepository.getJobById(id);
    }

    @Transactional
    public void deleteOrInsertNewLiked(Long jobId, String userName) {
        Optional<Liked> liked = likedRepository.getLikedByJobIdAndUserName(jobId, userName);
        System.out.println(liked.isPresent());
        liked.ifPresentOrElse(liked1 -> likedRepository.delete(liked1), () ->
                likedRepository.save(
                        new Liked(jobRepository.getById(jobId), userRepository.getUserByUserName(userName))
                ));
    }


    public boolean isLiked(Long jobId, String userName) {
        return likedRepository.getLikedByJobIdAndUserName(jobId, userName).isPresent();
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAllByActiveIsTrueOrderByIdDesc();
    }

    public Page<Job> findPaginatedJobs(Pageable pageable) {
        List<Job> jobs = getAllJobs();
        System.out.println(jobs.size());
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Job> list;

        if (jobs.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, jobs.size());
            list = jobs.subList(startItem, toIndex);
        }

        Page<Job> jobPage
                = new PageImpl<Job>(list, PageRequest.of(currentPage, pageSize), jobs.size());

        return jobPage;
    }
}
