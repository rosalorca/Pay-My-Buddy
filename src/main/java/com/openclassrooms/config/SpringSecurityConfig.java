package com.openclassrooms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
            http.formLogin()
                    .loginPage("/login")
                    .usernameParameter("email")
                    .passwordParameter("password");
            http.authorizeRequests()
                    .antMatchers("/home", "/transaction", "/account", "/profile", "myFriend");
            http.authorizeRequests().antMatchers("/registration", "/login")
                    .permitAll();
            http.logout()
                    .logoutUrl("/logout");

    }

}