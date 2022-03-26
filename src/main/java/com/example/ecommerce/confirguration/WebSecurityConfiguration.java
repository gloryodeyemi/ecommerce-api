package com.example.ecommerce.confirguration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST,"/user/register").permitAll()
                .antMatchers(HttpMethod.GET, "/h2-console/login.jsp").permitAll()
                .antMatchers(HttpMethod.GET, "/h2-console/login.do").permitAll()
                .antMatchers(HttpMethod.GET, "/h2-console").permitAll()
                .antMatchers(HttpMethod.POST, "/h2-console/test.do").permitAll()
                .antMatchers(HttpMethod.POST, "/h2-console/login.do").permitAll()
                .antMatchers(HttpMethod.POST,"/user/login").permitAll()
                .antMatchers(HttpMethod.POST,"/role").permitAll()
                .antMatchers(HttpMethod.POST,"/category").permitAll()
                .antMatchers(HttpMethod.POST,"/product/{userId}").permitAll()
                .antMatchers(HttpMethod.POST,"/cart/add/{userId}").permitAll()
                .antMatchers(HttpMethod.GET,"/cart/{userId}").permitAll()
                .antMatchers(HttpMethod.POST,"/order/checkout/{userId}").permitAll()
                .antMatchers(HttpMethod.GET,"/order/{userId}").permitAll()
//                .antMatchers(HttpMethod.GET,"/exploreCourse").permitAll()
                .anyRequest().authenticated();
    }

}
