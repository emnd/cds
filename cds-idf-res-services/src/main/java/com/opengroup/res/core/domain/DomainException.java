package com.opengroup.res.core.domain;

/**
 * Define the main typical Exception of Services and Domain layer to throw when
 * a problem occur
 *
 * @author Open groupe
 * @since 1.0.0
 */
public class DomainException extends Exception {

    /**
     * Throw an exception with an explicit message
     *
     * @param message
     */
    public DomainException(String message) {
        super(message);
    }

    /**
     * Throw an exception with an explicit message and the related cause
     *
     * @param message
     * @param cause
     */
    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
