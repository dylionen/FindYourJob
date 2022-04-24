package com.job.findyourjob.modules.jobs.liked;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LikedController {
    private final LikedRepository likedRepository;

    public LikedController(LikedRepository likedRepository) {
        this.likedRepository = likedRepository;
    }

}
