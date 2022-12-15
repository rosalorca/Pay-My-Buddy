package com.openclassrooms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/Users")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/home", "/transaction/**", "/profile", "/contact", "/User/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/404");
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        // BCrypt ---> il s'agit d'un des algorithmes d'encodage mot de passe
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

     /*   UserDetails user = User
                .withUsername("ozlem@gmail.com")
                .password(passwordEncoder().encode("abcdef"))
                .roles("USER_ROLE")
                .build();
        return new InMemoryUserDetailsManager(user);

        auth.inMemoryAuthentication()
                .withUser("ozlem@gmail.com").password("abcdef").roles("user");
    }*/}
}
