package com.opengroup.res.jpa;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.opengroup.res.jpa.entities.Request;

/**
 * Created by EXT_LHA88 on 13/10/2016.
 */
public interface RequestRepository extends CrudRepository<Request, Long> {

    List <Request> findByRequestDate(Date requestDate1);

    @Query ("SELECT decider, requestDate, replyDate FROM Request r WHERE r.applicant = ?1")
    List<Request> searchInApplicant(String decider, Date requestDate, Date replyDate);

    List <Request> findAll();

}
