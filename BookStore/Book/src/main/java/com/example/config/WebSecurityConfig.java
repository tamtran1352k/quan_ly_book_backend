package com.example.config;

import com.example.model.Role;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(crsf -> crsf.disable())
                .authorizeRequests()
                .requestMatchers("/home").permitAll()
                .requestMatchers("/admin/**").hasAnyRole("ADMIN")// Cho phép tất cả mọi người truy cập vào 2 địa chỉ này
                .anyRequest().authenticated()
                .and()
                .formLogin(login -> login.loginPage("/home/login")
                        .loginProcessingUrl("/home/login").successForwardUrl("/home")
                        .defaultSuccessUrl("/home")
                        .usernameParameter("email")
                        .passwordParameter("password").permitAll())
                .logout(logout -> logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/home").permitAll())
        ;
        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        List<com.example.model.User> userList = userRepository.findAll();
        List<UserDetails> userDetailsList = new ArrayList<>();
        for (com.example.model.User userModel : userList) {
            Role role = roleRepository.findById(userModel.getRoleId()).orElse(null);
            if (role != null) {
                UserDetails user = User.withUsername(userModel.getEmail())
                        .password(passwordEncoder().encode(userModel.getPassword()))
                        .roles(role.getName())
                        .build();
                userDetailsList.add(user);
            } else {
                UserDetails user = User.withUsername(userModel.getEmail())
                        .password(passwordEncoder().encode(userModel.getPassword()))
                        .roles("")
                        .build();
                userDetailsList.add(user);
            }

        }


        return new InMemoryUserDetailsManager(userDetailsList);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return encoder;
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers("/assets/**", "/upload/**", "/api/**");
    }
}