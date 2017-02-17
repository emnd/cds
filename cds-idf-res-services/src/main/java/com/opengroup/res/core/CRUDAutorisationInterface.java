package com.opengroup.res.core;

import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.core.domain.DomainCollaborator;
import com.opengroup.res.core.domain.DomainRequest;
import com.opengroup.res.core.domain.DomainProject;

import java.util.Date;
import java.util.List;


/**
 * A util interface to use with specified parameter services
 * @param <T>
 */
public interface CRUDAutorisationInterface<T> {

    /**
     * Create and store in the system, with the initial state and the owner of the creation step
     *
     * @param domainCollaborator
     * @param domainRequest
     * @param periodStart
     * @param periodEnd
     * @param boolean
     * @param motive
     * @param domainProject
     * @param equipement
     * @throws DomainException
     */
    void create(DomainCollaborator domainCollaborator, DomainRequest domainRequest, Date periodStart, Date periodEnd, boolean equipement, String motive, DomainProject domainProject) throws DomainException;

    /**
     * @param domainCollaborator
     * @param domainRequest
     * @param periodStart
     * @param periodEnd
     * @param boolean
     * @param motive
     * @param domainProject
     * @throws DomainException
     */
    void update(Long id, DomainCollaborator domainCollaborator, DomainRequest domainRequest, Date periodStart, Date periodEnd, boolean equipement, String motive, DomainProject domainProject) throws DomainException;


    /**
     * @param domainCollaborator
     * @param domainRequest
     * @param periodStart
     * @param periodEnd
     * @param boolean
     * @param motive
     * @param domainProject
     * @throws DomainException
     */
    void delete(Long id, DomainCollaborator domainCollaborator, DomainRequest domainRequest, Date periodStart, Date periodEnd, boolean equipement, String motive, DomainProject domainProject) throws DomainException;

    /**
     * List all autorisations of the system
     *
     * @return List<Autorisation>
     * @throws DomainException
     */
    List<T> list() throws DomainException;
}
