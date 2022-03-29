package com.job.findyourjob;

import com.job.findyourjob.modules.users.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class FindYourJobApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(FindYourJobApplication.class, args);
        // test
        /*
            ROLES:
            ADMIN
            EMPLOYER
            EMPLOYEE
         */

        UserService userService = context.getBean(UserService.class);
        RoleController roleController = context.getBean(RoleController.class);
        roleController.newRole(1L,"ADMIN","Administrator");
        roleController.newRole(1L,"EMPLOYER","Pracodawca");
        roleController.newRole(1L,"EMPLOYEE","Pracownik");

        Set<Role> roles = new HashSet<>();
        roles.add(roleController.getRole(1L));

        User admin = new User(1L,"admin","admin",true,"ADMIN","admin",roles);
        userService.createNewUser(admin);
    }

}
