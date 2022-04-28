package com.job.findyourjob;

import com.job.findyourjob.modules.users.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
//@EnableJpaRepositories(basePackageClasses = UserRepository.class)
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
        roleController.newRole(2L,"EMPLOYER","Pracodawca");
        roleController.newRole(3L,"EMPLOYEE","Pracownik");

        Set<Role> roles = new HashSet<>();
        roles.add(roleController.getRole(1L));
        roles.add(roleController.getRole(2L));

        User admin = new User(1L,"admin","$2a$12$pqlgwxC7C9G490XeXCT0WuP8.1KAoTdY8taQtlbatFfe0VBKv/ipS",true,"ADMIN","admin","asd@2p.pl",roles);
        userService.createNewUser(admin);

    }

}
