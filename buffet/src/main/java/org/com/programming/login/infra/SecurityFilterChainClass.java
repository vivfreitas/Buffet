package org.com.programming.login.infra;

import org.com.programming.login.infra.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityFilterChainClass {
//    LEMBRANDO QUE, PARA CONFIGURAÇÃO DO CORS MVC, É NECESSÁRIO USAR O QUE TEMOS ABAIXO. EM CASO DE MICRO, A CONFIG MUDA.
    private final JwtFilter jwtFilter;
    private final AuthenticationProvider authenticationProvider;
    @Autowired
    public SecurityFilterChainClass(JwtFilter jwtFilter, AuthenticationProvider authenticationProvider) {
        this.jwtFilter = jwtFilter;
        this.authenticationProvider = authenticationProvider;
    }
    @Bean
    public CorsConfigurationSource corsWebFilter(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://127.0.0.1:5500");
        configuration.addAllowedOrigin("http://localhost:5500");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.csrf(
                AbstractHttpConfigurer::disable)
                .cors(c -> c.configurationSource(corsWebFilter()))
                .authorizeHttpRequests(
                        auth -> {
                            auth.requestMatchers("/user/login").permitAll(); /* PermiteAll -> liberado para qualquer ROLE. */
                            auth.anyRequest().authenticated();
                        })
                .sessionManagement( session -> session.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS
                ))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
