package com.opengroup.res.core;

import com.opengroup.res.core.domain.DomainProject;
import com.opengroup.res.jpa.entities.Project;
import com.opengroup.res.core.domain.DomainException;

import java.util.Set;
import java.util.Date;

/**
 * Define the API to manipulate parameter
 *
 * @author Open groupe
 * @since 1.0.0
 */
public interface ProjectServices {

    /**
     * Create and store a new Project in the system, with the initial state and the owner of the creation step
     *
     * @param nameProject
     * @param periodStart
     * @param periodEnd
     * @throws DomainException
     */
    void createProject(String nameProject, Date periodStart, Date periodEnd) throws DomainException;

    /**
     * @param nameProject
     * @param periodStart
     * @param periodEnd
     * @throws DomainException
     */
    void updateProject(Long id, String nameProject, Date periodStart, Date periodEnd) throws DomainException;


    /**
     * @param nameProject
     * @param periodStart
     * @param periodEnd
     * @throws DomainException
     */
    void deleteProject(Long id, String nameProject, Date periodStart, Date periodEnd) throws DomainException;

    /**
     * List all Projects of the system
     *
     * @return List<Project>
     * @throws DomainException
     */
    Set<DomainProject> listAll() throws DomainException;
    
    
    public DomainProject findProject(Long id) throws DomainException ;
    
    public Project findProject(String projectName,Date periodStart, Date periodEnd) throws DomainException;
    
    Project findProject(String projectName) throws DomainException;

    /**
     * A generic creation api method
     * @param typedProject
     * @param <T> A specialized Project
     * @throws DomainException
     */
    <T extends DomainProject> void createProject(T typedProject) throws DomainException;

    /**
     * A generic update api method
     * @param typedParameter
     * @param <T> A specialized Project
     * @throws DomainException
     */
    <T extends DomainProject> void updateProject(T typedProject) throws DomainException;

    /**
     * A generic delete api method
     * @param typedProject
     * @param <T> A specialized Project
     * @throws DomainException
     */
    <T extends DomainProject> void deleteProject(T typedProject) throws DomainException;

	
}
