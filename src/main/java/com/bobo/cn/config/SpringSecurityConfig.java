package com.bobo.cn.config;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
 
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
 
    @Autowired
    private PasswordEncoder passwordEncoder;
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder.encode("1234"))
                .authorities("product");
    }
}