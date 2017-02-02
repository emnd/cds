package com.opengroup.res.core;

import java.util.Set;

import com.opengroup.res.core.domain.DomainCustomer;
import com.opengroup.res.core.domain.DomainException;


public interface CustomerServices {

  
    void createCustomer( String nameCustomer) throws DomainException;

    void updateCustomer( Long idCustomer, String nameCustomer) throws DomainException;

    void deleteCustomer( Long idCustomer) throws DomainException;

 
    Set<DomainCustomer> listAll() throws DomainException;

    <T extends DomainCustomer> void createCustomer(T typedCustomer) throws DomainException;

   
    <T extends DomainCustomer> void updateCustomer(T typedCustomer) throws DomainException;

 
    <T extends DomainCustomer> void deleteCustomer(T typedCustomer) throws DomainException;

	
}
