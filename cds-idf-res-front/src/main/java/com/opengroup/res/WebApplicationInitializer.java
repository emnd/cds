package com.opengroup.res;

import com.opengroup.res.organization.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.http.HttpMethod;

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
        /*http.authorizeRequests()
               .antMatchers("/app/layout/pages/login.template.html").anonymous()
               .anyRequest()
               .authenticated()
               .and()
                .formLogin()
                .loginPage("/app/layout/pages/login*")
                .defaultSuccessUrl("/dashbord/#gestion-des-autorisations/liste-des-demandes")
                .failureUrl("/app/layout/pages/login.template.html?error=true")
                .and()
                .logout().logoutSuccessUrl("/app/layout/pages/login.template.html")
              //.csrf()
              .disable()   // à rajouter pour le post
                .httpBasic();
       /* http.csrf().disable() // disable csrf for our requests.
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST,"/login").permitAll()
                .anyRequest().authenticated();*/
       /* http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll().and().httpBasic();*/
        http
                .httpBasic().and()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable()

        ;
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
