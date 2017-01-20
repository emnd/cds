package com.opengroup.res.util;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Spring REST Exception
 *
 * @author Open group
 * @since 1.0.0
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FrontException extends Exception{

    /**
     * Throw an exception with an explicit message
     *
     * @param message
     */
    public FrontException(String message) {
        super(message);
    }

    /**
     * Throw an exception with an explicit message and the related cause
     *
     * @param message
     * @param cause
     */
    public FrontException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     *
     * @param message
     * @param cause
     * @param request
     */
    public FrontException(String message, Throwable cause, HttpRequest request){
        super("A problem was encountered when trying to respond to a "
                +request.getMethod()+ " request at "
                +request.getURI()+ " caused by :" + message, cause);
    }
}
