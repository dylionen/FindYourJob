package com.job.findyourjob.modules.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    public void createNewUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void createNewUser(UserRegistrationDTO dto) {
        System.out.println("Próba dodania");
        System.out.println(dto);
        // find role
        Set<Role> roleSet = new HashSet<>();
        System.out.println(dto.getRole());
        Optional<Role> role = roleRepository.findByRoleName(dto.getRole());
        role.ifPresentOrElse(
                r -> roleSet.add(r),
                () -> {
                    throw new NoSuchElementException("Role not found");
                }
        );

        User user = new User(dto, roleSet);
        //user.setPassword();
        userRepository.save(user);
    }
}
