package com.opengroup.res.business.domain;

import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.core.domain.DomainParameter;

import java.io.Serializable;

/**
 * A parameter abstraction
 */
public class Product extends DomainParameter implements Serializable {

    /**
     * Domain parameter default constructor
     *
     * @param key
     * @param value
     * @param userId
     * @param state
     */
    private Product(String key, String value, String userId, ParameterStatus state) throws DomainException {
        super(ParameterContext.PRODUCT, key, value, userId, state);
    }

    /**
     * An easiest factory method to created state instance
     * @param key
     * @param value
     * @param userId
     * @return
     * @throws DomainException
     */
    public static Product newCreatedStateInstance(String key, String value, String userId) throws DomainException {
        return new Product(key, value, userId, ParameterStatus.CREATED );
    }

    /**
     * An easiest factory method to modified state instance
     * @param key
     * @param value
     * @param userId
     * @return
     * @throws DomainException
     */
    public static Product newModifiedStateInstance(String key, String value, String userId) throws DomainException {
        return new Product(key, value, userId, ParameterStatus.MODIFIED );
    }

    /**
     * An easiest factory method to suppressed state instance
     * @param key
     * @param value
     * @param userId
     * @return
     * @throws DomainException
     */
    public static Product newSuppressedStateInstance(String key, String value, String userId) throws DomainException {
        return new Product(key, value, userId, ParameterStatus.SUPPRESSED );
    }

    /**
     * An easiest factory method to validated state instance
     * @param key
     * @param value
     * @param userId
     * @return
     * @throws DomainException
     */
    public static Product newValidatedStateInstance(String key, String value, String userId) throws DomainException {
        return new Product(key, value, userId, ParameterStatus.VALIDATED );
    }
}
