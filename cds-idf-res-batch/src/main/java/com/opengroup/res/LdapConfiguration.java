package com.opengroup.res;

import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.organization.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

/**
 * This Spring configuration class is related to LDAP. It must respect the layers architecture, so any connectors call services to access to the infrastructure LDAP layer.
 * The abstraction is made through a UserServices interface
 *
 * @author Open groupe
 * @since 1.0.0
 */
@Configuration
public class LdapConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(LdapConfiguration.class);

    @Autowired
    private UserServices userServices;

    /**
     * Store an LDAP Context Source in Spring so it can be auto wired
     *
     * @return org.springframework.ldap.core.support.LdapContextSource
     */
    @Bean
    public LdapContextSource contextSource() {
        try {
            return (LdapContextSource) userServices.getContextSource();
        } catch (DomainException e) {
            LOGGER.info("Cannot construct a context source", e);
            throw new NullPointerException();
        }
    }

    /**
     * Store an LDAP Template in Spring context so it can be auto wired
     *
     * @return org.springframework.ldap.core.LdapTemplate
     */
    @Bean
    public LdapTemplate ldapTemplate() {
        return new LdapTemplate(contextSource());
    }
}