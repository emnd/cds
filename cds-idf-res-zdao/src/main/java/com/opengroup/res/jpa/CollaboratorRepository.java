package com.opengroup.res.jpa;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.opengroup.res.jpa.entities.Collaborator;


/**
 * Created by EXT_IDA43 on 12/10/2016.
 */

public interface CollaboratorRepository  extends CrudRepository<Collaborator, Long> {

    //Case sensitive
    List<Collaborator> getByLastName(String lastName);
    Collaborator getFirstByLastName(String lastName);
    List<Collaborator> getByLoginOpen(String login);
    Collaborator getFirstByLoginOpen(String login);


    //same search with JPQL
    @Query("SELECT c FROM Collaborator c WHERE " +
            "LOWER(c.firstName) LIKE LOWER(CONCAT('%',:searchTerm, '%')) OR " +
            "LOWER(c.lastName) LIKE LOWER(CONCAT('%',:searchTerm, '%'))")
    List<Collaborator> searchInName(@Param("searchTerm") String searchTerm);


    List<Collaborator> findAll();

    //JPQL does not have a LIMIT 1 option, so we have to return a list
    @Query("SELECT c FROM Collaborator c WHERE "
            + "LOWER(c.firstName) = LOWER(?1) "
            + "AND LOWER(c.lastName) = LOWER(?2) "
        )
    List<Collaborator> findByName( String firstName, String lastName);
    
    
    Collaborator findByLoginOpenAndEmailOpen(String loginOpen, String emailOpen);
}

