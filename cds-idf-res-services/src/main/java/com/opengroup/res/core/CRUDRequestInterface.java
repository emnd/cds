package com.opengroup.res.core;

import com.opengroup.res.core.domain.DomainException;

import java.util.List;
import java.util.Date;


/**
 * A util interface to use with specified parameter services
 * @param <T>
 */
public interface CRUDRequestInterface<T> {

    /**
     * Create and store in the system, with the initial state and the owner of the creation step
     *
     * @param applicant
     * @param decider
     * @param requestDate
     * @param replyDate
     * @throws DomainException
     */
    void create(String applicant, String decider, Date requestDate, Date replyDate, String applicantEmail) throws DomainException;

    /**
     * @param applicant
     * @param decider
     * @param requestDate
     * @param replyDate
     * @throws DomainException
     */
    void update(Long id, String applicant, String decider, Date requestDate, Date replyDate, String applicantEmail) throws DomainException;


    /**
     * @param applicant
     * @param decider
     * @param requestDate
     * @param replyDate
     * @throws DomainException
     */
    void delete(Long id, String applicant, String decider, Date requestDate, Date replyDate, String applicantEmail) throws DomainException;

    /**
     * List all requests of the system
     *
     * @return List<Request>
     * @throws DomainException
     */
    List<T> list() throws DomainException;
}
