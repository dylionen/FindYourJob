package com.job.findyourjob.modules.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Set;

@Controller
public class RoleController {
    @Autowired
    RoleRepository roleRepository;

    public void newRole(Long id, String name, String details) {
        roleRepository.save(new Role(id, name, details));
    }
    public Role getRole(Long id){
        return roleRepository.getById(id);
    }
}
