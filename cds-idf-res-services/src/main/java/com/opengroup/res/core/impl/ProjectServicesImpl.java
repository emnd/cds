package com.opengroup.res.core.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opengroup.res.core.ProjectServices;
import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.core.domain.DomainProject;
import com.opengroup.res.core.impl.mappers.ProjectMapper;
import com.opengroup.res.jpa.ProjectRepository;
import com.opengroup.res.jpa.entities.Project;

/**
 * Implementation to manage project service
 *
 * @author Open groupe
 * @since 1.0.0
 */
@Service
public class ProjectServicesImpl implements ProjectServices {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    @Transactional
    public void createProject(String nameProject, Date periodStart,
			Date periodEnd) throws DomainException {
            DomainProject domainProject = DomainProject.createProject(nameProject, periodStart, periodEnd);
        projectRepository.save(projectMapper.toOneEntity(domainProject));
       
    }

    @Override
    @Transactional
    public void updateProject(Long idProject, String nameProject, Date periodStart,
			Date periodEnd) throws DomainException {
      
        DomainProject domainProject = DomainProject.updateProject(idProject,nameProject, periodStart, periodEnd);
        Project project = projectRepository.findOne(idProject);
        if (project == null) {
            throw new DomainException("This project does not exist");
        }
        project.setNameProject(domainProject.getNameProject());

        projectRepository.save(project);
    }

    
	@SuppressWarnings("unused")
	@Override
    @Transactional
    public void deleteProject(Long idProject) throws DomainException {
      
        DomainProject domainProject = DomainProject.deleteProject(idProject);
        Project existingProject = projectRepository.findOne(idProject);
       if (existingProject == null) {
            throw new DomainException("This project has not be found");}
       else
       {
    	   projectRepository.delete(existingProject);
       }
        
   }

  

    @Override
    @Transactional
    public Set<DomainProject> listAll() throws DomainException {
        return new HashSet<>(projectMapper.toDomains(projectRepository.findAll()));
    }

    @Override
    public <T extends DomainProject> void createProject(T typedProject) throws DomainException {
        createProject(typedProject.getNameProject(), typedProject.getPeriodStart(), typedProject.getPeriodEnd());
    }

    @Override
    public <T extends DomainProject> void updateProject(T typedProject) throws DomainException {
        updateProject(typedProject.getIdProject(), typedProject.getNameProject(), typedProject.getPeriodStart(), typedProject.getPeriodEnd());
    }

    @Override
    public <T extends DomainProject> void deleteProject(T typedProject) throws DomainException {
        deleteProject(typedProject.getIdProject());
    }
}