package com.example.TestSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(login -> login
                        .loginPage("/login")
                        .loginProcessingUrl("/loginProc"))
//                .httpBasic(Customizer.withDefaults())
                .sessionManagement(auth -> auth
                        .sessionFixation().changeSessionId()
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "/join", "/joinProc").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated());

        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails user1 = User.builder()
//                .username("user1")
//                .password(bCryptPasswordEncoder().encode("1234"))
//                .roles("ADMIN")
//                .build();
//        UserDetails user2 = User.builder()
//                .username("user2")
//                .password(bCryptPasswordEncoder().encode("1234"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1, user2);
//    }
}
