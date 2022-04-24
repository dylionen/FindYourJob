package com.job.findyourjob.modules.jobs.liked;

import com.job.findyourjob.modules.jobs.Job;
import com.job.findyourjob.modules.users.User;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Liked {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @OneToOne
    private Job job;

    private Timestamp likedDate;
}
