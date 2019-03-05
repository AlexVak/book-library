package com.alexvak.booklibrary.config;

import com.alexvak.booklibrary.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final SecurityUtility securityUtility;

    private static final String[] PUBLIC_MATCHERS = {
            "/h2-console/**",
            "/book/**"
    };

    public SecurityConfig(UserService userService, SecurityUtility securityUtility) {
        this.userService = userService;
        this.securityUtility = securityUtility;
    }

    private BCryptPasswordEncoder passwordEncoder() {
        return securityUtility.passwordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().cors().disable().httpBasic()
                .and().authorizeRequests().antMatchers(PUBLIC_MATCHERS)
                .permitAll().anyRequest().authenticated();
        httpSecurity.headers().frameOptions().disable();
    }


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

}
