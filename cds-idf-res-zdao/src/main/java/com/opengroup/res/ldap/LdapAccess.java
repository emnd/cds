package com.opengroup.res.ldap;

import com.opengroup.res.util.ZdaoException;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

/**
 * This interface provides access rules to interact with a LDAP. It is related to Spring Framework
 *
 * @author Open groupe
 * @since 1.0.0
 */
public interface LdapAccess {

    /**
     * Authentication process
     *
     * @param auth org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
     * @throws ZdaoException
     */
    void authenticate(AuthenticationManagerBuilder auth) throws ZdaoException;

    /**
     * @return org.springframework.ldap.core.support.LdapContextSource
     * @throws ZdaoException
     */
    LdapContextSource getContext() throws ZdaoException;
}
