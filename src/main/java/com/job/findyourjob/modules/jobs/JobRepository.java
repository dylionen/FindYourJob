package com.job.findyourjob.modules.jobs;

import com.job.findyourjob.modules.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> getJobsByCreatedBy(User createdBy);

    List<Job> findAllByOrderByIdDesc();
    Optional<Job> getJobById(Long id);
}
