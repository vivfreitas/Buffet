package org.com.programming.login.jpa;

import org.com.programming.login.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpa extends JpaRepository<UserEntity, Long> {

    UserEntity findByNameUser(String login);
}
