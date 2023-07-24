package com.boardapplication.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

         http.csrf().disable()
            .authorizeRequests()
            //.mvcMatchers("/**").permitAll()


                .antMatchers("/login").not().fullyAuthenticated() //
                .antMatchers("/user/**", "/board/**").authenticated()
                .antMatchers(HttpMethod.GET, "/user/{username}").authenticated()
                //.antMatchers("/board/{id}").authenticated().access("hasRole('')")


            .anyRequest().authenticated()
            .and()

                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
            .and()
                 .logout().logoutSuccessUrl("/");
            return http.build();}
}

