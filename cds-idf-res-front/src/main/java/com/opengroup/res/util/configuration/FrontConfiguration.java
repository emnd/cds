package com.opengroup.res.util.configuration;

/**
 * It is a configuration facade to uniquely access to the related properties used by this layer
 *
 * @author Open groupe
 * @since 1.0.0
 */
public interface FrontConfiguration {

    /**
     * getTomcatAJPPort()
     *
     * @return String value
     */
    Integer getTomcatAJPPort();

    /**
     * getTomcatAJPEnabled()
     *
     * @return String value
     */
    Boolean getTomcatAJPEnabled();
}
