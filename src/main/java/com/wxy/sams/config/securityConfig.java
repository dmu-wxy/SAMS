package com.wxy.sams.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.wxy.sams.model.Manager;
import com.wxy.sams.model.RespBean;
import com.wxy.sams.service.impl.ManagerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class securityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ManagerServiceImpl managerServiceImpl;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    protected void confiure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(managerServiceImpl);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/doLogin")
                .loginPage("/login")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter writer = resp.getWriter();
                        Manager manager = (Manager)authentication.getPrincipal();
                        manager.setPassword("");
                        RespBean ok = RespBean.ok("登录成功！",manager);
                        String s = new ObjectMapper().writeValueAsString(ok);
                        writer.write(s);
                        writer.flush();
                        writer.close();
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter writer = resp.getWriter();
                        RespBean error = RespBean.error("登录失败！");
                        if(e instanceof LockedException){
                            error.setMsg("账户被锁定，请联系系统管理员！");
                        }else if(e instanceof CredentialsExpiredException){
                            error.setMsg("密码过期，请联系系统管理员！");
                        }else if(e instanceof AccountExpiredException){
                            error.setMsg("账户过期，请联系系统管理员！");
                        }else if(e instanceof DisabledException){
                            error.setMsg("账户被禁用，请联系系统管理员！");
                        }else if(e instanceof BadCredentialsException){
                            error.setMsg("账户或密码错误");
                        }
                        writer.write(new ObjectMapper().writeValueAsString(error));
                        writer.flush();
                        writer.close();
                    }
                })
                .permitAll()
                .and()
                .logout()
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter writer = resp.getWriter();
                        writer.write(new ObjectMapper().writeValueAsString(RespBean.ok("注销成功！")));
                        writer.flush();
                        writer.close();
                    }
                })
                .permitAll()
                .and()
                .csrf().disable();
    }
}
