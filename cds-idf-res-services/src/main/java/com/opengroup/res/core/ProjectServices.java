package com.opengroup.res.core;

import java.util.Date;
import java.util.Set;

import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.core.domain.DomainProject;


public interface ProjectServices {

  
    void createProject( String nameProject, Date periodStart,
			Date periodEnd) throws DomainException;

    void updateProject( Long idProject, String nameProject, Date periodStart,
			Date periodEnd) throws DomainException;

    void deleteProject( Long idProject) throws DomainException;

 
    Set<DomainProject> listAll() throws DomainException;

    <T extends DomainProject> void createProject(T typedProject) throws DomainException;

   
    <T extends DomainProject> void updateProject(T typedProject) throws DomainException;

 
    <T extends DomainProject> void deleteProject(T typedProject) throws DomainException;

	
}
