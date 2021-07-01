//package com.codegym.config;
//
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    public void configure(AuthenticationManagerBuilder a) throws Exception {
//        a.inMemoryAuthentication()
//                .withUser("user").password("{noop}123456").roles("USER")
//                .and()
//                .withUser("admin").password("{noop}123456").roles("ADMIN");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/api/categories").permitAll()
//                .and()
//                .authorizeRequests().antMatchers(HttpMethod.GET, "/api/categories**").permitAll()
//                .and()
//                .authorizeRequests().antMatchers(HttpMethod.PUT, "/api/categories**").hasRole("ADMIN")
//                .and()
//                .authorizeRequests().antMatchers(HttpMethod.POST, "/api/categories**").hasRole("ADMIN")
//                .and()
//                .authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/categories**").hasRole("ADMIN")
//                .and()
//                .formLogin()
//                .and()
//                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
//    }
//}
