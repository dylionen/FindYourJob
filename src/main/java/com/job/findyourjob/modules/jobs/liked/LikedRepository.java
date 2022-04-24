package com.job.findyourjob.modules.jobs.liked;

import com.job.findyourjob.modules.jobs.Job;
import com.job.findyourjob.modules.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LikedRepository extends JpaRepository<Liked, Long> {

    Liked getLikedByUserAndJob(User user, Job job);

    @Query("FROM Liked l where l.user.id = :userId and l.job.id =:jobId ")
    Liked getLikedByUserIdAndJobId(Long userId, Long jobId);


}
