package com.temzu.market_project.mscore.repositories;

import com.temzu.market_project.mscore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByLogin(String login);
}
