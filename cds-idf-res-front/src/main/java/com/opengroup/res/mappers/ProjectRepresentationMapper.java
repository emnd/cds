package com.opengroup.res.mappers;


import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.core.domain.DomainProject;
//import com.opengroup.res.util.FrontException;
import com.opengroup.res.util.mappers.AbstractFrontMapper;
import com.opengroup.res.model.ProjectRepresentation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Parameter representation mapper
 *
 * @author Open group
 * @since 1.0.0
 */
@Component
public class ProjectRepresentationMapper extends AbstractFrontMapper<DomainProject, ProjectRepresentation> {

    @Override
    public DomainProject toOneDomain(ProjectRepresentation representation) throws DomainException {
    	 return new DomainProject(representation.getProjectName(),representation.getPeriodStart(), representation.getPeriodEnd(),representation.getId());
    }

    @Override
    public ProjectRepresentation toOneRepresentation(DomainProject domain){
    	ProjectRepresentation projectRepresentation = new ProjectRepresentation();
    	projectRepresentation.setId(domain.getId());
    	projectRepresentation.setProjectName(domain.getProjectName());
    	projectRepresentation.setPeriodStart(domain.getPeriodStart());
    	projectRepresentation.setPeriodEnd(domain.getPeriodEnd());
        return projectRepresentation;
    }
    
    public List<ProjectRepresentation> toRepresentationList(List<DomainProject> domainList)
    {
    	if (domainList == null) {

			return null;
		}
    	
    	List<ProjectRepresentation> representationList = new ArrayList<ProjectRepresentation>();
    	for (DomainProject domainProject : domainList) {

			representationList.add(toOneRepresentation(domainProject));
		}
    	return representationList;
    }
    
    public List<DomainProject> convertListRepresentationToListDomainList(List<ProjectRepresentation> representationList) throws DomainException
    {
    	if (representationList == null) {

			return null;
		}

		List<DomainProject> listDomain = new ArrayList<DomainProject>();

		for (ProjectRepresentation ProjectRepresentation : representationList) {

			listDomain.add(toOneDomain(ProjectRepresentation));
		}

		return listDomain;
    }
    
    public List<ProjectRepresentation> convertListDomainListToListRepresentation(List<DomainProject> domainList) throws DomainException
    {
    	if (domainList == null) {

			return null;
		}

		List<ProjectRepresentation> listRepresentation = new ArrayList<ProjectRepresentation>();

		for (DomainProject domainProject : domainList) {

			listRepresentation.add(toOneRepresentation(domainProject));
		}

		return listRepresentation;
    }
}
