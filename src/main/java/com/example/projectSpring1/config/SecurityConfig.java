package com.example.projectSpring1.config;

import com.example.projectSpring1.service.EmployeeUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private  EmployeeUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
        @Bean
        public DaoAuthenticationProvider authProvider(){
            DaoAuthenticationProvider autherProvider = new DaoAuthenticationProvider();
            autherProvider.setUserDetailsService(userDetailsService);
            autherProvider.setPasswordEncoder(passwordEncoder());
            autherProvider.setHideUserNotFoundExceptions(false);
            return  autherProvider;
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws  Exception{
        return config.getAuthenticationManager();
        }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf->csrf.disable()).authorizeHttpRequests(auth->auth
                        .requestMatchers("/api/**","/").permitAll()
                        .anyRequest().authenticated())
                        .sessionManagement(sesstion->sesstion
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
