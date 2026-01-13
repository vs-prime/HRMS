package com.magadhUniversity.hrms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers("/login", "/static/**", "/css/**", "/error").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/attendance/**").hasAnyRole("ADMIN", "MANAGER")
                .requestMatchers("/leaveRequest/**").hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")
                .requestMatchers("/submitLeaveRequest/**").hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")
                .requestMatchers("/userAttendance/**").hasRole("USER")
                .requestMatchers("/manager/**").hasRole("MANAGER")
                .requestMatchers("/userDashboard/**").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/perform_login")
                        .successHandler(authenticationSuccessHandler())
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .csrf();  // Re-enable CSRF protection
        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            if (authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
                response.sendRedirect("/admin");
            } else if (authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_MANAGER"))) {
                response.sendRedirect("/manager");
            } else if (authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_EMPLOYEE"))) {
                response.sendRedirect("/employee");
            } else {
                response.sendRedirect("/userDashboard");
            }
        };
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build());
        manager.createUser(User.withUsername("managerCSE")
                .password(passwordEncoder().encode("password"))
                .roles("MANAGER", "EMPLOYEE")
                .build());
        manager.createUser(User.withUsername("managerISE")
                .password(passwordEncoder().encode("password"))
                .roles("MANAGER", "EMPLOYEE")
                .build());
        manager.createUser(User.withUsername("managerECE")
                .password(passwordEncoder().encode("password"))
                .roles("MANAGER", "EMPLOYEE")
                .build());
        manager.createUser(User.withUsername("managerEEE")
                .password(passwordEncoder().encode("password"))
                .roles("MANAGER", "EMPLOYEE")
                .build());
        manager.createUser(User.withUsername("managerMBA")
                .password(passwordEncoder().encode("password"))
                .roles("MANAGER", "EMPLOYEE")
                .build());
        manager.createUser(User.withUsername("managerMCA")
                .password(passwordEncoder().encode("password"))
                .roles("MANAGER", "EMPLOYEE")
                .build());
        manager.createUser(User.withUsername("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build());
        manager.createUser(User.withUsername("employee")
                .password(passwordEncoder().encode("password"))
                .roles("EMPLOYEE")
                .build());
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
