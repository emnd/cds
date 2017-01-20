package com.opengroup.res;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Open Group
 */
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan({"com.opengroup.res.core","com.opengroup.res.business", "com.opengroup.res.jpa"})
public class IntegrationTestsConfiguration {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(IntegrationTestsConfiguration.class, "--debug");
    }
}

