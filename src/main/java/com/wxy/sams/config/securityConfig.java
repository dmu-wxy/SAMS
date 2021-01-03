package com.wxy.sams.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class securityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests()   //开启配置
//            .antMatchers("/res/**", "/admin", "/thirdparty/**", "/auth/login").permitAll() //设置路径
//            .antMatchers("/admin/**").hasAuthority("admin:index") //hasAuthority 必须有。。。角色
//            .anyRequest().authenticated()  //其他路径认证之后就能访问
//            .and()
//            .formLogin().loginPage("/admin").permitAll() //登录表单   处理登录请求的url  允许所有
//            .and()
//            .logout().logoutUrl("/admin/logout").logoutSuccessUrl("/admin").invalidateHttpSession(true)
//            .and()
//            .csrf().disable()  // 要用postman接口测试，关闭csrf攻击  登录是post请求
//            .headers().frameOptions().sameOrigin();
        http.requestMatcher(EndpointRequest.toAnyEndpoint())
                .authorizeRequests()
                .anyRequest().hasRole("admin")
                .and()
                .httpBasic();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    protected void confiure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("meteor").password("$2a$10$9BisuXCe6ZFRorvgs1CFiOjN/PR0/EqcUod7h5NBb3NVCbgwoIdRi").roles("admin")
                .and()
                .withUser("smartdog").password("$2a$10$/55kdJmt.NMyeFdaCJR0w.rfYpHvs1Na7MSz4pCQxEvdU27yr4uB6").roles("user");

        //auth.userDetailsService(managerServiceImpl);
    }

    /**
     * 角色继承关系
     * @return
     */
    @Bean
    RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        //dba有admin的权限，admind有user的权限，以换行符分割
        String hierarchy = "ROLE_dba > ROLE_admin \n ROLE_admin > ROLE_user";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }
}
