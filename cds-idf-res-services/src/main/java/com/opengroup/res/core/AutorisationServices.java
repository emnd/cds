package com.opengroup.res.core;

//import com.opengroup.res.core.domain.DomainParameter;
import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.core.domain.DomainAutorisation;
import com.opengroup.res.core.domain.DomainCollaborator;
import com.opengroup.res.core.domain.DomainRequest;
import com.opengroup.res.core.domain.DomainProject;

import java.util.Date;
import java.util.Set;

/**
 * Define the API to manipulate Autorisation
 *
 * @author Open groupe
 * @since 1.0.0
 */
public interface AutorisationServices {

    /**
     * Create and store a new Autorisation in the system, with the initial state and the owner of the creation step
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
    void createAutorisation(DomainCollaborator domainCollaborator, DomainRequest domainRequest, Date periodStart, Date periodEnd, boolean equipement, String motive,String status, DomainProject domainProject) throws DomainException;

    /**
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
    void updateAutorisation(Long id, DomainCollaborator domainCollaborator, DomainRequest domainRequest, Date periodStart, Date periodEnd, boolean equipement, String motive, String status,DomainProject domainProject) throws DomainException;


    /**
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
    void deleteAutorisation(Long id, DomainCollaborator domainCollaborator, DomainRequest domainRequest, Date periodStart, Date periodEnd, boolean equipement, String motive,String status, DomainProject domainProject) throws DomainException;

    /**
     * List all Autorisations of the system
     *
     * @return List<Autorisation>
     * @throws DomainException
     */
    Set<DomainAutorisation> listAll() throws DomainException;

    /**
     * A generic creation api method
     * @param typedAutorisation
     * @param <T> A specialized Autorisation
     * @throws DomainException
     */
    <T extends DomainAutorisation> void createAutorisation(T typedAutorisation) throws DomainException;

    /**
     * A generic update api method
     * @param typedAutorisation
     * @param <T> A specialized Autorisation
     * @throws DomainException
     */
    <T extends DomainAutorisation> void updateAutorisation(T typedAutorisation) throws DomainException;

    /**
     * A generic delete api method
     * @param typedAutorisation
     * @param <T> A specialized Autorisation
     * @throws DomainException
     */
    <T extends DomainAutorisation> void deleteAutorisation(T typedAutorisation) throws DomainException;
}
