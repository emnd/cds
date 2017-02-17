package com.opengroup.res;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.opengroup.res.organization.UserServices;



//import com.opengroup.res.organization.UserServices;

/**
 * This is where we configure the web application server instance and authentication policy
 *
 * @author Open groupe
 * @since 1.0.0
 */
@SpringBootConfiguration
@EnableWebSecurity
@EnableAutoConfiguration
@ComponentScan
public class WebApplicationInitializer extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServices userServices;

    /**
     * Entry point of the application
     *
     * @param args
     * @throws Exception
     */

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebApplicationInitializer.class, args);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                
                .and()
                .csrf().disable()
                .httpBasic();
    }

    /**
     * Transfer an AuthenticationManagerBuilder
     *
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        userServices.connect(auth);
    }
}
