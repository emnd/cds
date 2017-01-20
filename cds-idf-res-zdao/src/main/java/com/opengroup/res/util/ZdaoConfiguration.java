package com.opengroup.res.util;

/**
 * It is a configuration facade to uniquely access to the related properties used by this layer
 *
 * @author Open groupe
 * @since 1.0.0
 */
public interface ZdaoConfiguration {

    /**
     * getJpaDatasourceScmUrl()
     *
     * @return String value
     */
    String getJpaDatasourceScmUrl();

    /**
     * getJpaDatasourceScmUrl()
     *
     * @return String value
     */
    String getJpaDatasourceScmUsername();

    /**
     * getJpaDatasourceScmUrl()
     *
     * @return String value
     */
    String getJpaDatasourceScmPassword();

    /**
     * getJpaDatasourceScmUrl()
     *
     * @return String value
     */
    String getJpaDatasourceScmDriverClassName();

    /**
     * getJpaDatasourceScmUrl()
     *
     * @return String value
     */
    String getJpaDatasourceScmDdlAuto();

    /**
     * getJpaDatasourceScmUrl()
     *
     * @return String value
     */
    String getLdapDatasourceScmUserSearchBase();

    /**
     * getJpaDatasourceScmUrl()
     *
     * @return String value
     */
    String getLdapDatasourceScmUserSearchFilter();

    /**
     * getJpaDatasourceScmUrl()
     *
     * @return String value
     */
    String getLdapDatasourceScmGroupSearchBase();

    /**
     * getJpaDatasourceScmUrl()
     *
     * @return String value
     */
    String getLdapDatasourceScmGroupSearchFilter();

    /**
     * getJpaDatasourceScmUrl()
     *
     * @return String value
     */
    String getLdapDatasourceScmUrl();

    /**
     * getJpaDatasourceScmUrl()
     *
     * @return String value
     */
    String getLdapDatasourceScmLogin();

    /**
     * getJpaDatasourceScmUrl()
     *
     * @return String value
     */
    String getLdapDatasourceScmPassword();
}
