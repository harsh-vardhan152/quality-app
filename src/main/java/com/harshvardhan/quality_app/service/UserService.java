package com.harshvardhan.quality_app.service;


import com.harshvardhan.quality_app.DTO.RegisterRequest;
import com.harshvardhan.quality_app.entity.Role;
import com.harshvardhan.quality_app.entity.RoleName;
import com.harshvardhan.quality_app.entity.User;
import com.harshvardhan.quality_app.repository.RoleRepository;
import com.harshvardhan.quality_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    public User register(RegisterRequest registerRequest) {

        User user = new User();
        user.setName(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());

        Set<Role> roles = new HashSet<>();
        if (registerRequest.getRoles() == null || registerRequest.getRoles().isEmpty()) {
            Role roleByName = roleRepository.findByName(RoleName.DEFAULT);
            if (roleByName == null) {
                throw new RuntimeException("Default role is not added to you please contact with admin");
            }
            roles.add(roleByName);
        } else {
            for (String role : registerRequest.getRoles()) {
                try {
                    RoleName roleName = RoleName.valueOf(role.toUpperCase());
                    Role DBRole = roleRepository.findByName(roleName);
                    if (DBRole == null) {
                        throw new RuntimeException("User Roles is not found in the Database " + roleName);
                    }
                    roles.add(DBRole);
                } catch (IllegalArgumentException e) {
                    throw new RuntimeException("Invalid Roles is specified:" + role);
                }
            }
        }
        user.setRoles(roles);
        return userRepository.save(user);
    }

    //By this services user can see all the user
    public List<User> getallusers() {
        return userRepository.findAll();
    }
}
