package com.opengroup.res.util;

/**
 * A technical Exception related to DAO layers problems
 *
 * @author Open groupe
 * @since 1.0.0
 */
public class ZdaoException extends Exception {

    /**
     * @param message
     */
    public ZdaoException(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public ZdaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
