package com.opengroup.res.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Open Group
 */
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan({"fr.odeadom.scm.organization", "fr.odeadom.scm.ldap", "fr.odeadom.scm.util"})
public class LdapIntegrationTestsConfiguration {

    @Autowired
    private UserServices userServices;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(LdapIntegrationTestsConfiguration.class);
    }
}

