package com.opengroup.res.util;

/**
 * Java constants key map with application.properties
 *
 * @author Open groupe
 * @since 1.0.0
 */
public final class ZdaoApplicationPropertiesKeys {

    /** */
    public static final String JPA_DATASOURCE_SCM_URL = "spring.datasource.url";
    /** */
    public static final String JPA_DATASOURCE_SCM_USERNAME = "spring.datasource.username";
    /** */
    public static final String JPA_DATASOURCE_SCM_USERPA = "spring.datasource.password";
    /** */
    public static final String JPA_DATASOURCE_SCM_DRIVER_CLASS_NAME = "spring.datasource.driver-class-name";
    /** */
    public static final String JPA_DATASOURCE_SCM_DDL_AUTO = "spring.jpa.hibernate.ddl-auto=create-update";
    /** */
    public static final String LDAP_DATASOURCE_SCM_USER_SEARCH_BASE = "security.ldap.userSearchBase";
    /** */
    public static final String LDAP_DATASOURCE_SCM_USER_SEARCH_FILTER = "security.ldap.userSearchFilter";
    /** */
    public static final String LDAP_DATASOURCE_SCM_GROUP_SEARCH_BASE = "security.ldap.groupSearchBase";
    /** */
    public static final String LDAP_DATASOURCE_SCM_GROUP_SEARCH_FILTER = "security.ldap.groupSearchFilter";
    /** */
    public static final String LDAP_DATASOURCE_SCM_URL = "security.ldap.ldapUrl";
    /** */
    public static final String LDAP_DATASOURCE_SCM_LOGIN = "security.ldap.ldapLogin";
    /** */
    public static final String LDAP_DATASOURCE_SCM_USERPA = "security.ldap.ldapPassword";

    private ZdaoApplicationPropertiesKeys() {
    }
}