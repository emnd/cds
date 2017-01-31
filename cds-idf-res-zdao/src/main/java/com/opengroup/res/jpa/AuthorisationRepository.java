package com.opengroup.res.jpa;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.opengroup.res.jpa.entities.Authorisation;
import com.opengroup.res.jpa.entities.Collaborator;

/**
 * Created by EXT_IDA43 on 13/10/2016.
 */
public interface AuthorisationRepository extends CrudRepository<Authorisation, Long> {



    @Query ("SELECT a FROM Authorisation a WHERE a.collaborator = ?0")
    List<Authorisation> searchByCollaborator(Collaborator collaborator);


    List<Authorisation> findAll();

    @Query("SELECT a FROM Authorisation a " +
            "                      WHERE a.request.applicant = ?1")
    List<Authorisation> findByApplicant(String applicant);


    @Query("SELECT a FROM Authorisation a " +
            "                      WHERE ?1 BETWEEN a.periodStart AND a.periodEnd" +
            "                       AND a.status = 'Acceptée'"               //TODO: change this to the value or key from Parameter Table
        )
      public List<Authorisation> activeAuthorisations (Date date);

}
