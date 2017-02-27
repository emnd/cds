package com.opengroup.res.core;

import com.opengroup.res.core.domain.DomainRequest;
import com.opengroup.res.core.domain.DomainException;

import java.util.Date;
import java.util.Set;

/**
 * Define the API to manipulate Request
 *
 * @author Open groupe
 * @since 1.0.0
 */
public interface RequestServices {

    /**
     * Create and store a new Request in the system, with the initial state and the owner of the creation step
     *
     * @param applicant
     * @param decider
     * @param requestDate
     * @param replyDate
     * @param applicantEmail
     * @throws DomainException
     */
    void createRequest(String applicant, String decider, Date requestDate, Date replyDate, String applicantEmail) throws DomainException;

    /**
     * @param applicant
     * @param decider
     * @param requestDate
     * @param replyDate
     * @throws DomainException
     */
    void updateRequest(Long id, String applicant, String decider, Date requestDate, Date replyDate, String applicantEmail) throws DomainException;


    /**
     * @param applicant
     * @param decider
     * @param requestDate
     * @param replyDate
     * @throws DomainException
     */
    void deleteRequest(Long id, String applicant, String decider, Date requestDate, Date replyDate, String applicantEmail) throws DomainException;

    /**
     * List all requests of the system
     *
     * @return List<Request>
     * @throws DomainException
     */
    Set<DomainRequest> listAll() throws DomainException;
    
    public DomainRequest findRequest(Long id) throws DomainException;
    public DomainRequest findRequest(String applicant,Date requestDate) throws DomainException;

    /**
     * A generic creation api method
     * @param typedRequset
     * @param <T> A specialized request
     * @throws DomainException
     */
    <T extends DomainRequest> void createRequest(T typedRequest) throws DomainException;

    /**
     * A generic update api method
     * @param typedRequest
     * @param <T> A specialized request
     * @throws DomainException
     */
    <T extends DomainRequest> void updateRequest(T typedRequest) throws DomainException;

    /**
     * A generic delete api method
     * @param typedRequest
     * @param <T> A specialized request
     * @throws DomainException
     */
    <T extends DomainRequest> void deleteRequest(T typedRequest) throws DomainException;
}
