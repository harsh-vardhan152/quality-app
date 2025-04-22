package com.harshvardhan.quality_app.CommandLineRunner;

import com.harshvardhan.quality_app.entity.Role;
import com.harshvardhan.quality_app.entity.RoleName;
import com.harshvardhan.quality_app.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class RoleSeeder implements CommandLineRunner {
    /**
     * @param args
     * @throws Exception
     */
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

        for(RoleName roleName: RoleName.values()){
            if (!roleRepository.existsByName(roleName))
            {
                Role role =new Role();
                role.setName(roleName);
                roleRepository.save(role);
            }
        }

    }
}
