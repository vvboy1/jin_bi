package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //主路径可以访问
                .antMatchers("/").permitAll()
                //其他都要验证
                .anyRequest().authenticated()
                .and()
                //注销任意权限可以访问
                .logout().permitAll()
                .and()
                //注销也是任意权限可以访问
                .formLogin();
        //关闭默认csrf认证
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //要忽略的静态资源
        web.ignoring().antMatchers("/js/**","/css/**","/images/**");
    }
}
