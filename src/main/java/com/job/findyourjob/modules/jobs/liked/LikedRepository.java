package com.job.findyourjob.modules.jobs.liked;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LikedRepository extends JpaRepository<Liked, Long> {

    @Query("from Liked L WHERE  L.user.userName = :userName and L.job.id= :jobId")
    Optional<Liked> getLikedByJobIdAndUserName(Long jobId, String userName);
}
