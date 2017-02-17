package com.opengroup.res.core;

import com.opengroup.res.core.domain.DomainException;

import java.util.List;
import java.util.Date;


/**
 * A util interface to use with specified parameter services
 * @param <T>
 */
public interface CRUDProjectInterface<T> {

    /**
     * Create and store in the system, with the initial state and the owner of the creation step
     *
     * @param nameProject
     * @param periodStart
     * @param periodEnd
     * @throws DomainException
     */
    void create(String nameProject, Date periodStart, Date periodEnd) throws DomainException;

    /**
     * @param nameProject
     * @param periodStart
     * @param periodEnd
     * @throws DomainException
     */
    void update(Long id, String nameProject, Date periodStart, Date periodEnd) throws DomainException;


    /**
     * @param nameProject
     * @param periodStart
     * @param periodEnd
     * @throws DomainException
     */
    void delete(Long id, String nameProject, Date periodStart, Date periodEnd) throws DomainException;

    /**
     * List all projects of the system
     *
     * @return List<Project>
     * @throws DomainException
     */
    List<T> list() throws DomainException;
}
