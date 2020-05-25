package com.kode4you.getyourquote.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebConfig extends WebSecurityConfigurerAdapter {

    //@Autowired
    //private SecureUserDetailService userDetailService;

    @Override
    protected void configure(HttpSecurity http){
//        http.csrf().disable().authorizeRequests()
//                .antMatchers(HttpMethod.POST, "/user/user-login").permitAll()
//                .antMatchers(HttpMethod.POST, "/user/update-password-temp").permitAll()
//                .antMatchers(HttpMethod.POST, "/user/send-email").permitAll()
//                .antMatchers(HttpMethod.POST, "/user/add/user").permitAll()
//                .antMatchers(HttpMethod.POST, "/user/auth").permitAll()
//                .antMatchers(HttpMethod.POST, "/user/test").permitAll()
//                .anyRequest().authenticated().and()
//                .addFilterBefore(new JWTLoginFilter("/user/auth", authenticationManager(), userRepository, userService), UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        //auth.userDetailsService(userDetailsService);
    }

}
