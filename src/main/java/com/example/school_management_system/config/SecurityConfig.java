package com.example.school_management_system.config;

import com.example.school_management_system.security.OAuthSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final OAuthSuccessHandler oAuthSuccessHandler;
    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter,OAuthSuccessHandler oAuthSuccessHandler) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.oAuthSuccessHandler=oAuthSuccessHandler;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http)throws  Exception{

       return http.authorizeHttpRequests(auth->{
            auth.requestMatchers("/api/v1/student/**").hasAnyRole("Admin","Student")
                    .requestMatchers("/api/v1/parent/**").hasAnyRole("Admin","Teacher","Parent")
                    .requestMatchers("/api/v1/teacher/**").hasAnyRole("Admin","Teacher").anyRequest().permitAll();
        }).cors(cors-> cors.configurationSource(corsConfigurationSource()))
               .csrf(csrf->{csrf.disable();}).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
               .oauth2Login(oauth -> oauth
                       .loginPage("/oauth2/authorization/google")
                       .successHandler(oAuthSuccessHandler)
               ).build();

    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)throws Exception{
        return configuration.getAuthenticationManager();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration=new CorsConfiguration();
        configuration.setAllowedOrigins(   List.of("http://localhost:3000"));
        configuration.setAllowedMethods( List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders( List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource sourse=new UrlBasedCorsConfigurationSource();
        sourse.registerCorsConfiguration("/**",configuration);
        return  sourse;
    }
    }
