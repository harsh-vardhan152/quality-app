package com.harshvardhan.quality_app.repository;

import com.harshvardhan.quality_app.entity.Role;
import com.harshvardhan.quality_app.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(RoleName name);

    boolean existsByName(RoleName name);
}
