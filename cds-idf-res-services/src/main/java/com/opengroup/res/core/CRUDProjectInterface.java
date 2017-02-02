package com.opengroup.res.core;

import com.opengroup.res.core.domain.DomainException;

import java.util.Date;
import java.util.List;


/**
 * A util interface to use with specified project services
 * @param <T>
 */
public interface CRUDProjectInterface<T> {

    void create(Long idProject, String nameProject, Date periodStart,
			Date periodEnd) throws DomainException;

   
    void update(Long idProject, String nameProject, Date periodStart,
			Date periodEnd) throws DomainException;


    void delete(Long idProject, String nameProject, Date periodStart,
			Date periodEnd) throws DomainException;

    /**
     * List all projects of the system
     *
     * @return List<Project>
     * @throws DomainException
     */
    List<T> list() throws DomainException;
}
