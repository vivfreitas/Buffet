package org.com.programming.login.service;

import org.com.programming.login.entities.UserEntity;
import org.com.programming.login.infra.authManagerConfig.ApplicationConfig;
import org.com.programming.login.jpa.UserJpa;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserJpa userJpa;
    private final ApplicationConfig applicationConfig;

    public UserService(UserJpa userJpa, ApplicationConfig applicationConfig){
        this.userJpa = userJpa;
        this.applicationConfig = applicationConfig;
    }

    public UserEntity createUsuario(UserEntity entity){
        entity.setPasswordUser(applicationConfig.passwordEncoder().encode(entity.getPasswordUser()));
        return userJpa.save(entity);
    }
}
