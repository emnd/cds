package com.opengroup.res.core.impl.mappers;

import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.core.domain.DomainProject;
import com.opengroup.res.jpa.entities.Project;
import com.opengroup.res.util.AbstractDomainMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
//import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * A parameter mapper
 */
@Component
public class ProjectMapper extends AbstractDomainMapper<DomainProject, Project> {

    @Override
    public DomainProject toOneDomain(Project entity) throws DomainException {
        Long id = entity.getIdProject();
        String projectName = entity.getNameProject();
        Date periodStart = entity.getPeriodStart();
        Date periodEnd = entity.getPeriodEnd();


        return new DomainProject(projectName,periodStart, periodEnd,id);
    }

    @Override
    public Project toOneEntity(DomainProject domain) {
        Project project = new Project(domain.getId(),domain.getProjectName(), domain.getPeriodStart(), domain.getPeriodEnd());
        //project.setId(domain.getId());
        return project;
    }
    
    public List<Project> convertDomainListToEntityList(List<DomainProject> listDomain) {

		if (listDomain == null) {

			return null;
		}

		List<Project> listEntity = new ArrayList<Project>();

		for (DomainProject domainProject : listDomain) {

			listEntity.add(toOneEntity(domainProject));
		}

		return listEntity;
	}
    
    
    public List<DomainProject> convertEntityListToDomainList(List<Project> listEntity) throws DomainException {

		if (listEntity == null) {

			return null;
		}

		List<DomainProject> listDomain = new ArrayList<DomainProject>();

		for (Project project : listEntity) {

			listDomain.add(toOneDomain(project));
		}

		return listDomain;
	}
}
