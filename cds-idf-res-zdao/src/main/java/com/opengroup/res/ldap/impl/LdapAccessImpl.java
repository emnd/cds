package com.opengroup.res.ldap.impl;

import com.opengroup.res.ldap.LdapAccess;
import com.opengroup.res.util.ZdaoConfiguration;
import com.opengroup.res.util.ZdaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Repository;

/**
 * This is the implementation of LDAP access with Spring Framework
 *
 * @author Open groupe
 * @since 1.0.0
 */
@Repository
public class LdapAccessImpl implements LdapAccess {

    @Autowired
    private ZdaoConfiguration zdaoConfiguration;

    @Override
    public void authenticate(AuthenticationManagerBuilder auth) throws ZdaoException {
        try {
            auth.ldapAuthentication()
                    .userSearchBase(zdaoConfiguration.getLdapDatasourceScmUserSearchBase())
                    .userSearchFilter(zdaoConfiguration.getLdapDatasourceScmUserSearchFilter())
                    .groupSearchBase(zdaoConfiguration.getLdapDatasourceScmGroupSearchBase())
                    .groupSearchFilter(zdaoConfiguration.getLdapDatasourceScmGroupSearchFilter())
                    .contextSource()
                    .url(zdaoConfiguration.getLdapDatasourceScmUrl())
                    .managerDn(zdaoConfiguration.getLdapDatasourceScmLogin())
                    .managerPassword(zdaoConfiguration.getLdapDatasourceScmPassword());
        } catch (Exception e) {
            throw new ZdaoException("An error occurs while authenticating", e);
        }
    }

    @Override
    public LdapContextSource getContext() {
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl(zdaoConfiguration.getLdapDatasourceScmUrl());
        contextSource.setUserDn(zdaoConfiguration.getLdapDatasourceScmLogin());
        contextSource.setPassword(zdaoConfiguration.getLdapDatasourceScmPassword());
        return contextSource;
    }
}
