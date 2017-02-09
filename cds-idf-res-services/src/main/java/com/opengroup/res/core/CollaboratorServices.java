package com.opengroup.res.core;

//import com.opengroup.res.core.domain.DomainParameter;
import com.opengroup.res.core.domain.DomainException;

import java.util.Set;
import com.opengroup.res.core.domain.DomainCollaborator;
/**
 * Define the API to manipulate collaborator
 *
 * @author Open groupe
 * @since 1.0.0
 */
public interface CollaboratorServices {

    /**
     * Create and store a new collaborator in the system, with the initial state and the owner of the creation step
     *
     * @param loginOpen
     * @param emailOpen
     * @param firstName
     * @param lastName
     * @param bu
     * @throws DomainException
     */
    void createCollaborator(String loginOpen, String firstName, String lastName, String emailOpen, String bu) throws DomainException;

    /**
     * @param loginOpen
     * @param emailOpen
     * @param firstName
     * @param lastName
     * @param bu
     * @throws DomainException
     */
    void updateCollaborator(Long id, String loginOpen, String firstName, String lastName, String emailOpen, String bu) throws DomainException;


    /**
     * @param loginOpen
     * @param emailOpen
     * @param firstName
     * @param lastName
     * @param bu
     * @throws DomainException
     */
    void deleteCollaborator(Long id, String loginOpen, String firstName, String lastName, String emailOpen, String bu) throws DomainException;

    /**
     * List all collaborators of the system
     *
     * @return List<Collaborator>
     * @throws DomainException
     */
    Set<DomainCollaborator> listAll() throws DomainException;

    /**
     * A generic creation api method
     * @param typedCollaborator
     * @param <T> A specialized collaborator
     * @throws DomainException
     */
    <T extends DomainCollaborator> void createCollaborator(T typedCollaborator) throws DomainException;

    /**
     * A generic update api method
     * @param typedCollaborator
     * @param <T> A specialized collaborator
     * @throws DomainException
     */
    <T extends DomainCollaborator> void updateCollaborator(T typedCollaborator) throws DomainException;

    /**
     * A generic delete api method
     * @param typedCollaborator
     * @param <T> A specialized Collaborator
     * @throws DomainException
     */
    <T extends DomainCollaborator> void deleteCollaborator(T typedCollaborator) throws DomainException;
}
