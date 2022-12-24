package com.javatechie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UpgradeSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new EmployeeUserDetailsService();
    }

//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//        UserDetails user = User.withUsername("Basant")
//                .password(passwordEncoder.encode("Pwd1"))
//                .roles("EMPLOYEE")
//                .build();
//
//        UserDetails admin = User.withUsername("Santosh")
//                .password(passwordEncoder.encode("Pwd2"))
//                .roles("MANAGER", "HR")
//                .build();
//
//        UserDetails hr = User.withUsername("Shruti")
//                .password(passwordEncoder.encode("Pwd3"))
//                .roles("HR")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin, hr);
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.csrf().disable()
//                .authorizeRequests().antMatchers("/text").permitAll()
//                .and()
//                .authorizeRequests().antMatchers("/greeting", "/text/**").authenticated()
//                .and().httpBasic()
//                .and().build();

        return http.csrf().disable()
                .authorizeRequests().antMatchers("/employees/welcome", "/employees/create").permitAll()
                .and()
                .authorizeRequests().antMatchers("/employees/**").authenticated()
                .and().httpBasic()
                //.and().formLogin()
                .and().build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
