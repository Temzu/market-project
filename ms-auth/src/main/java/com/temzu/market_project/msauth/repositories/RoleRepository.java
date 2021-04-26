package com.temzu.market_project.msauth.repositories;

import com.temzu.market_project.msauth.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);

}