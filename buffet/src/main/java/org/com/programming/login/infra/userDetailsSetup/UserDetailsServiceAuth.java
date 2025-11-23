package org.com.programming.login.infra.userDetailsSetup;

import org.com.programming.login.entities.UserEntity;
import org.com.programming.login.jpa.UserJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceAuth implements UserDetailsService {

    private final UserJpa userJpa;

    @Autowired
    public UserDetailsServiceAuth(UserJpa userJpa){
        this.userJpa = userJpa;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserEntity entity = userJpa.findByNameUser(login);

        if (entity == null){
            throw new UsernameNotFoundException("Usuário não localizado.");
        }
        return entity;
    }
}
