package com.opengroup.res.core;

import com.opengroup.res.core.domain.DomainParameter;
import com.opengroup.res.core.domain.DomainException;

import java.util.Set;

/**
 * Define the API to manipulate parameter
 *
 * @author Open groupe
 * @since 1.0.0
 */
public interface ParameterServices {

    /**
     * Create and store a new parameter in the system, with the initial state and the owner of the creation step
     *
     * @param context
     * @param code
     * @param value
     * @param userId
     * @throws DomainException
     */
    void createParameter(DomainParameter.ParameterContext context, String code, String value, String userId) throws DomainException;

    /**
     * @param context
     * @param code
     * @param value
     * @param userId
     * @throws DomainException
     */
    void updateParameter(DomainParameter.ParameterContext context, String code, String value, String userId) throws DomainException;


    /**
     * @param context
     * @param code
     * @param userId
     * @throws DomainException
     */
    void deleteParameter(DomainParameter.ParameterContext context, String code, String userId) throws DomainException;

    /**
     * List all parameters of the system
     *
     * @return List<Parameter>
     * @throws DomainException
     */
    Set<DomainParameter> listAll() throws DomainException;

    /**
     * A generic creation api method
     * @param typedParameter
     * @param <T> A specialized parameter
     * @throws DomainException
     */
    <T extends DomainParameter> void createParameter(T typedParameter) throws DomainException;

    /**
     * A generic update api method
     * @param typedParameter
     * @param <T> A specialized parameter
     * @throws DomainException
     */
    <T extends DomainParameter> void updateParameter(T typedParameter) throws DomainException;

    /**
     * A generic delete api method
     * @param typedParameter
     * @param <T> A specialized parameter
     * @throws DomainException
     */
    <T extends DomainParameter> void deleteParameter(T typedParameter) throws DomainException;
}
