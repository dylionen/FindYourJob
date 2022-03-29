package com.job.findyourjob.modules.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void createNewUser(User user) {
        userRepository.save(user);
    }
}
