package com.opengroup.res.jpa;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.opengroup.res.jpa.entities.Request;

/**
 * Created by open groupe.
 */
public interface RequestRepository extends CrudRepository<Request, Long> {

    List <Request> findByRequestDate(Date requestDate);

    @Query ("SELECT decider, requestDate, replyDate FROM Request r WHERE r.applicant = ?1")
    List<Request> searchInApplicant(String decider, Date requestDate, Date replyDate);

    List <Request> findAll();
    
    @Query ("SELECT applicant,decider, requestDate, replyDate FROM Request r WHERE r.applicant = ?1 and r.requestDate = ?2 ")
    Request findByApplicantAndRequestDate(String applicant,Date requestDate);
    

}
