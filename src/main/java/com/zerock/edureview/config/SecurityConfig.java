package com.zerock.edureview.config;

import com.zerock.edureview.security.handler.ClubLoginSuccessHandler;
import com.zerock.edureview.security.service.ClubUserDetailsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Log4j2
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ClubUserDetailsService userDetailsService;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
        http.authorizeRequests()
                .antMatchers("/sample/all").permitAll()
                .antMatchers("/sample/member").hasRole("USER");
        */
        http.formLogin(); //인가/인증에 문제시 로그인 화면
        http.csrf().disable();
        http.logout();
        http.oauth2Login().successHandler(successHandler());
        http.rememberMe().tokenValiditySeconds(60*60*7).userDetailsService(userDetailsService); //7days
    }

    @Bean
    public ClubLoginSuccessHandler successHandler(){
        return new ClubLoginSuccessHandler(passwordEncoder());
    }

    /*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{

        auth.inMemoryAuthentication().withUser("user1")
                .password("$2a$10$/pMsT0G0lVdaDEuWfP0NBeVDjBF1oVuaQURGVdDEhcGMDUEf21pXu")
                .roles("USER");
    }
    */
}
