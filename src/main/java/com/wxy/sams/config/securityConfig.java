package com.wxy.sams.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class securityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/res/**", "/admin", "/thirdparty/**", "/auth/login").permitAll()
            .antMatchers("/admin/**").hasAuthority("admin:index")
            .anyRequest().authenticated()
            .and()
            .formLogin().loginPage("/admin").permitAll()
            .and()
            .logout().logoutUrl("/admin/logout").logoutSuccessUrl("/admin").invalidateHttpSession(true)
            .and()
            .csrf().disable()
            .headers().frameOptions().sameOrigin();
    }
}
