package com.opengroup.res.core.domain;

/**
 * Exception related to print pdf
 *
 * @author Open group
 * @since 1.0.0
 */
public class PrintException extends Exception {

    /**
     * Throw an exception with an explicit message
     *
     * @param message
     */
    public PrintException(String message) {
        super(message);
    }

    /**
     * Throw an exception with an explicit message
     *
     * @param message
     * @param e
     */
    public PrintException(String message, Exception e) {
        super(message, e);
    }
}
