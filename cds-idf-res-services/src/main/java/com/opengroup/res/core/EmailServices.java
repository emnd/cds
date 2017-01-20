package com.opengroup.res.core;

import javax.mail.MessagingException;

/**
 * Define all email services.
 *
 * @author Open group
 * @since 1.0.0
 */
public interface EmailServices {

    /**
     * This is a dummy service - to be removed
     *
     * @param to
     * @param subject
     * @param body
     */
    void send(String to, String subject, String body) throws MessagingException;
}
