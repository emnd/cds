package com.opengroup.res.core;

import java.util.List;

import com.opengroup.res.core.domain.DomainException;


/**
 * A util interface to use with specified project services
 * @param <T>
 */
public interface CRUDCustomerInterface<T> {

    void create(Long idCustomer, String nameCustomer) throws DomainException;

   
    void update(Long idCustomer, String nameCustomer) throws DomainException;


    void delete(Long idCustomer, String nameCustome) throws DomainException;

    /**
     * List all projects of the system
     *
     * @return List<Project>
     * @throws DomainException
     */
    List<T> list() throws DomainException;
}
