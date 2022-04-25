package com.job.findyourjob.modules.jobs.liked;

import com.job.findyourjob.modules.jobs.Job;
import com.job.findyourjob.modules.users.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;

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
    @JoinColumn(name = "job_id")
    private Job job;

    private Timestamp likedDate;

    public Liked(Job job, User user) {
        this.job = job;
        this.user = user;
        this.likedDate = new Timestamp(Calendar.getInstance().getTimeInMillis());
    }
}
