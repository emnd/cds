package com.opengroup.res.core;

import com.opengroup.res.core.domain.DomainException;

import java.util.List;


/**
 * A util interface to use with specified parameter services
 * @param <T>
 */
public interface CRUDCollaboratorInterface<T> {

    /**
     * Create and store in the system, with the initial state and the owner of the creation step
     *
     * @param loginOpen
     * @param emailOpen
     * @param firstName
     * @param lastName
     * @param bu
     * @throws DomainException
     */
    void create(String loginOpen, String firstName, String lastName, String emailOpen, String bu) throws DomainException;

    /**
     * @param loginOpen
     * @param emailOpen
     * @param firstName
     * @param lastName
     * @param bu
     * @throws DomainException
     */
    void update(Long id, String loginOpen, String firstName, String lastName, String emailOpen, String bu) throws DomainException;


    /**
     * @param loginOpen
     * @param emailOpen
     * @param firstName
     * @param lastName
     * @param bu
     * @throws DomainException
     */
    void delete(Long id, String loginOpen, String firstName, String lastName, String emailOpen, String bu) throws DomainException;

    /**
     * List all collaborators of the system
     *
     * @return List<Collaborator>
     * @throws DomainException
     */
    List<T> list() throws DomainException;
}
