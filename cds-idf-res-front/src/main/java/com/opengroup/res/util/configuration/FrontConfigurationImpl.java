package com.opengroup.res.util.configuration;

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
public class FrontConfigurationImpl implements FrontConfiguration {

    @Autowired
    private Environment env;


    @Override
    public Integer getTomcatAJPPort() {
        return Integer.valueOf(env.getRequiredProperty(FrontApplicationPropertiesKeys.TOMCAT_AJP_PORT));
    }

    @Override
    public Boolean getTomcatAJPEnabled() {
        return Boolean.valueOf(env.getRequiredProperty(FrontApplicationPropertiesKeys.TOMCAT_AJP_ENABLED));
    }
}
