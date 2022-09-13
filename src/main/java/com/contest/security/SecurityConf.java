package com.contest.security;

import com.contest.model.security.User;
import com.contest.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConf extends WebSecurityConfigurerAdapter {

  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final RegistrationService userService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .cors()
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET , "/contest**").permitAll()
            .antMatchers(HttpMethod.GET , "/candidates**").permitAll()
            .antMatchers(HttpMethod.GET , "/year**").permitAll()
            .antMatchers(HttpMethod.PUT , "/contest/**").hasRole(User.Roles.ADMIN.toString())
            .antMatchers(HttpMethod.POST , "/login/**").hasRole(User.Roles.VISITOR.toString())
            .antMatchers(HttpMethod.POST, "/contest/**").hasRole(User.Roles.ADMIN.toString())
            .antMatchers(HttpMethod.PATCH, "/contest/**").hasRole(User.Roles.ADMIN.toString())
            .antMatchers(HttpMethod.DELETE, "/contest/**").hasRole(User.Roles.ADMIN.toString())
            .antMatchers(HttpMethod.PUT , "/candidates/**").hasRole(User.Roles.ADMIN.toString())
            .antMatchers(HttpMethod.POST, "/candidates/**").hasRole(User.Roles.ADMIN.toString())
            .antMatchers(HttpMethod.PATCH, "/candidates/**").hasRole(User.Roles.ADMIN.toString())
            .antMatchers(HttpMethod.DELETE, "/candidates/**").hasRole(User.Roles.ADMIN.toString())
            .antMatchers(HttpMethod.PUT , "/year/**").hasRole(User.Roles.ADMIN.toString())
            .antMatchers(HttpMethod.POST, "/year/**").hasRole(User.Roles.ADMIN.toString())
            .antMatchers(HttpMethod.PATCH, "/year/**").hasRole(User.Roles.ADMIN.toString())
            .antMatchers(HttpMethod.DELETE, "/year/**").hasRole(User.Roles.ADMIN.toString())
            .and()
            .formLogin()
            .and()
            .logout()
            .permitAll()
            .and()
            .csrf()
            .disable()
            .httpBasic();
  }

  @Bean
  public AuthenticationSuccessHandler successHandler() {
    SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler();
    handler.setUseReferer(true);
    return handler;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(daoAuthenticationProvider());
  }

  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(bCryptPasswordEncoder);
    provider.setUserDetailsService(userService);
    return provider;
  }

  @Override
  @Bean
  protected UserDetailsService userDetailsService() {
    return userService;
  }
}
