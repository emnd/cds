package com.opengroup.res.core;

import com.opengroup.res.core.domain.DomainException;

import java.util.List;


/**
 * A util interface to use with specified parameter services
 * @param <T>
 */
public interface CRUDParameterInterface<T> {

    /**
     * Create and store in the system, with the initial state and the owner of the creation step
     *
     * @param code
     * @param value
     * @param userId
     * @throws DomainException
     */
    void create(String code, String value, String userId) throws DomainException;

    /**
     * @param code
     * @param value
     * @param userId
     * @throws DomainException
     */
    void update(String code, String value, String userId) throws DomainException;


    /**
     * @param code
     * @param value
     * @param userId
     * @throws DomainException
     */
    void delete(String code, String value, String userId) throws DomainException;

    /**
     * List all parameters of the system
     *
     * @return List<Parameter>
     * @throws DomainException
     */
    List<T> list() throws DomainException;
}
