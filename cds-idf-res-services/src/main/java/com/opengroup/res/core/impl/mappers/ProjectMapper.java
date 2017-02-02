package com.opengroup.res.core.impl.mappers;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.core.domain.DomainProject;
import com.opengroup.res.jpa.entities.Project;
import com.opengroup.res.util.AbstractDomainMapper;

/**
 * A project mapper
 */
@Component
public class ProjectMapper extends AbstractDomainMapper<DomainProject, Project> {

    @SuppressWarnings("unused")
	@Override
    public DomainProject toOneDomain(Project entity) throws DomainException {
        Long idProject = entity.getIdProject();
        String nameProject = entity.getNameProject();
        Date periodStart=entity.getPeriodStart();
        Date periodEnd =entity.getPeriodEnd();
       
        return toOneDomain(entity);
		//return DomainProject(nameProject, periodStart, periodEnd);

    }

   

	@Override
    public Project toOneEntity(DomainProject domain) {
       
        Project project = new Project(
                domain.getIdProject(),
                domain.getNameProject(),domain.getPeriodStart(),domain.getPeriodEnd());
      
        return project;
    }
}
