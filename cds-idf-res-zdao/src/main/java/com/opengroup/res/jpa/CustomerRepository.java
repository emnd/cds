package com.opengroup.res.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.opengroup.res.jpa.entities.Customer;

/**
 * @author SGU15268
 *
 */
public interface CustomerRepository extends CrudRepository<Customer, Long>{

	 //Case sensitive

	
    

    //same search with JPQL
    @Query("SELECT cl FROM Customer cl WHERE " +
            "LOWER(cl.nameCustomer) LIKE LOWER(CONCAT('%',:searchTerm, '%'))")
    List<Customer> searchInNameCustomer(@Param("searchTerm") String searchTerm);


    List<Customer> findAll();

    //JPQL does not have a LIMIT 1 option, so we have to return a list
    @Query("SELECT cl FROM Customer cl WHERE "
            + "LOWER(cl.nameCustomer) = LOWER(?1) ")
         
    List<Customer> findNameCustomer(String  nameCustomer);  
    //List<Customer> findNameCustomer( String nameCustomer);
}
