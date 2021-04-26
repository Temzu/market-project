package com.temzu.market_project.msauth.repositories;

import com.temzu.market_project.msauth.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByLogin(String login);

}