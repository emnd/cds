package com.opengroup.res.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * This is a Spring Java config upon mail service
 *
 * @author Open group
 * @since 1.0.0
 */
@Configuration
public class MailConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailConfiguration.class);

    @Autowired
    private Environment env;

    /**
     * Store an LDAP Context Source in Spring so it can be auto wired
     *
     * @return org.springframework.ldap.core.support.LdapContextSource
     */
    @Bean
    public JavaMailSender javaMailSender() throws IOException {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(env.getProperty("spring.mail.host"));
        javaMailSender.setPort(Integer.valueOf(env.getProperty("spring.mail.port")));
        javaMailSender.setUsername(env.getProperty("spring.mail.username"));
        javaMailSender.setPassword(env.getProperty("spring.mail.password"));
        javaMailSender.setProtocol(env.getProperty("spring.mail.protocol"));
        Properties props = new Properties();
        props.load(new FileReader("src/it/resources/application.properties"));
        javaMailSender.setJavaMailProperties(props);
        return javaMailSender;
    }
}
