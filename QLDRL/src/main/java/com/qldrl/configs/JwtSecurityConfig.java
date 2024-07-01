/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.configs;

import com.qldrl.filters.CustomAccessDeniedHandler;
import com.qldrl.filters.JwtAuthenticationTokenFilter;
import com.qldrl.filters.RestAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.qldrl.controllers",
    "com.qldrl.repositories",
    "com.qldrl.services",
    "com.qldrl.components"
})
@Order(1)
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
        JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
        jwtAuthenticationTokenFilter.setAuthenticationManager(authenticationManager());
        return jwtAuthenticationTokenFilter;
    }

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/api/**");
        http.authorizeRequests().antMatchers("/api/login/").permitAll();
        http.authorizeRequests().antMatchers("/api/users/").permitAll();
        http.authorizeRequests().antMatchers("/api/activities/").permitAll();
        http.authorizeRequests().antMatchers("/api/activities/**").permitAll();
        http.authorizeRequests().antMatchers("/api/activities-hocky/**").permitAll();
        http.authorizeRequests().antMatchers("/api/hocky/").permitAll();
        http.authorizeRequests().antMatchers("/api/tlsv/diemrenluyen/nhapdiem/**").permitAll();
        http.authorizeRequests().antMatchers("/api/tlsv/diemrenluyen/nhapdiem-csv/**").permitAll();
        http.authorizeRequests().antMatchers("/api/tlsv/duyetbaothieu/**").permitAll();
        http.authorizeRequests().antMatchers("/api/tlsv/xoabaothieu/**").permitAll();
        http.authorizeRequests().antMatchers("/api/like/count/**").permitAll();
        http.authorizeRequests().antMatchers("/api/comments/**").permitAll();
        http.antMatcher("/api/**").httpBasic().authenticationEntryPoint(restServicesEntryPoint()).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/**").access("hasRole('CVCTSV') or hasRole('TLSV') or hasRole('SV')")
                .antMatchers(HttpMethod.POST, "/api/**").access("hasRole('CVCTSV') or hasRole('TLSV') or hasRole('SV')")
                .antMatchers(HttpMethod.DELETE, "/api/**").access("hasRole('CVCTSV') or hasRole('TLSV') or hasRole('SV')").and()
                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
    }
}
