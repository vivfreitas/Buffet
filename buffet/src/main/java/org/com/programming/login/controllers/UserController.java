package org.com.programming.login.controllers;

import org.com.programming.login.entities.DTOsAuth.AuthRequest;
import org.com.programming.login.entities.DTOsAuth.AuthResponse;
import org.com.programming.login.entities.UserEntity;
import org.com.programming.login.infra.jwt.JwtService;
import org.com.programming.login.infra.userDetailsSetup.UserDetailsServiceAuth;
import org.com.programming.login.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceAuth userDetailsService;
    private final JwtService jwtService;

    public UserController(UserService userService, AuthenticationManager authenticationManager, UserDetailsServiceAuth userDetailsService, JwtService jwtService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> userCreate(@RequestBody UserEntity entity){
        UserEntity obj = userService.createUsuario(entity);
        if (obj != null){
            return ResponseEntity.status(HttpStatus.OK).body("Usuário Criado.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não foi criado.");
    }

    @GetMapping("/me")
    public ResponseEntity<UserEntity> getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // Aqui representa o crachá do usuário que já está no bolso e verificado pelo JwtFilter.
        UserEntity userEntity = (UserEntity) authentication.getPrincipal(); // Dessa forma conseguimos pegar o usuário.
        return ResponseEntity.ok(userEntity);
    }

    @PostMapping("/login")
    public AuthResponse/*token*/loginUser(@RequestBody AuthRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.login(), request.senha())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.login());
        final String token = jwtService.generateToken(userDetails);
        return new AuthResponse(token);
    }
}
