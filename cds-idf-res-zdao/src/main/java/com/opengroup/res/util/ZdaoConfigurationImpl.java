package com.opengroup.res.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * The configuration facade implementation using Spring <code>Environment</code> instance
 *
 * @author Open groupe
 * @since 1.0.0
 */
@Service
public class ZdaoConfigurationImpl implements ZdaoConfiguration {

    @Autowired
    private Environment env;

    @Override
    public String getJpaDatasourceScmUrl() {
        return env.getRequiredProperty(ZdaoApplicationPropertiesKeys.JPA_DATASOURCE_SCM_URL);
    }

    @Override
    public String getJpaDatasourceScmUsername() {
        return env.getRequiredProperty(ZdaoApplicationPropertiesKeys.JPA_DATASOURCE_SCM_USERNAME);
    }

    @Override
    public String getJpaDatasourceScmPassword() {
        return env.getRequiredProperty(ZdaoApplicationPropertiesKeys.JPA_DATASOURCE_SCM_USERPA);
    }

    @Override
    public String getJpaDatasourceScmDriverClassName() {
        return env.getRequiredProperty(ZdaoApplicationPropertiesKeys.JPA_DATASOURCE_SCM_DRIVER_CLASS_NAME);
    }

    @Override
    public String getJpaDatasourceScmDdlAuto() {
        return env.getRequiredProperty(ZdaoApplicationPropertiesKeys.JPA_DATASOURCE_SCM_DDL_AUTO);
    }

    @Override
    public String getLdapDatasourceScmUserSearchBase() {
        return env.getRequiredProperty(ZdaoApplicationPropertiesKeys.LDAP_DATASOURCE_SCM_USER_SEARCH_BASE);
    }

    @Override
    public String getLdapDatasourceScmUserSearchFilter() {
        return env.getRequiredProperty(ZdaoApplicationPropertiesKeys.LDAP_DATASOURCE_SCM_USER_SEARCH_FILTER);
    }

    @Override
    public String getLdapDatasourceScmGroupSearchBase() {
        return env.getRequiredProperty(ZdaoApplicationPropertiesKeys.LDAP_DATASOURCE_SCM_GROUP_SEARCH_BASE);
    }

    @Override
    public String getLdapDatasourceScmGroupSearchFilter() {
        return env.getRequiredProperty(ZdaoApplicationPropertiesKeys.LDAP_DATASOURCE_SCM_GROUP_SEARCH_FILTER);
    }

    @Override
    public String getLdapDatasourceScmUrl() {
        return env.getRequiredProperty(ZdaoApplicationPropertiesKeys.LDAP_DATASOURCE_SCM_URL);
    }

    @Override
    public String getLdapDatasourceScmLogin() {
        return env.getRequiredProperty(ZdaoApplicationPropertiesKeys.LDAP_DATASOURCE_SCM_LOGIN);
    }

    @Override
    public String getLdapDatasourceScmPassword() {
        return env.getRequiredProperty(ZdaoApplicationPropertiesKeys.LDAP_DATASOURCE_SCM_USERPA);
    }
}
