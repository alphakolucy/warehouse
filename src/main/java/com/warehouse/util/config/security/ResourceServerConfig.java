package com.warehouse.util.config.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration; 

/**
 * Created by wangyunfei on 2017/6/12.
 */

//@Configuration
//@EnableResourceServer
//public class ResourceServerConfig  extends ResourceServerConfigurerAdapter {
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .exceptionHandling()
//                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
//            .and()
//                .authorizeRequests()
////                .antMatchers("/product").access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
//                .antMatchers("/product").access("hasAuthority('admin-menuaaaaadfadsfasdf')")
//                .antMatchers("/").permitAll()
//                .anyRequest().authenticated()
//            .and()
//                .httpBasic();
//    }
//} 
