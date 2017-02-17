package com.opengroup.res.mappers;

import com.opengroup.res.core.domain.DomainCollaborator;
import com.opengroup.res.core.domain.DomainException;
//import com.opengroup.res.util.FrontException;
import com.opengroup.res.util.mappers.AbstractFrontMapper;
import com.opengroup.res.model.CollaboratorRepresentation;

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
public class CollaboratorRepresentationMapper extends AbstractFrontMapper<DomainCollaborator, CollaboratorRepresentation> {

    @Override
    public DomainCollaborator toOneDomain(CollaboratorRepresentation representation) throws DomainException {
        return new DomainCollaborator(representation.getLoginOpen(),representation.getFirstName(),representation.getLastName(),representation.getEmailOpen(),representation.getBuOpen(), representation.getId());
    }

    @Override
    public CollaboratorRepresentation toOneRepresentation(DomainCollaborator domain){
    	CollaboratorRepresentation collaboratorRepresentation = new CollaboratorRepresentation();
        collaboratorRepresentation.setId(domain.getId());
        collaboratorRepresentation.setFirstName(domain.getFirstName());
        collaboratorRepresentation.setLastName(domain.getLastName());
        collaboratorRepresentation.setEmailOpen(domain.getLoginOpen());
        collaboratorRepresentation.setBuOpen(domain.getBuOpen());
        collaboratorRepresentation.setLoginOpen(domain.getLoginOpen());
        return collaboratorRepresentation;
    }
    
    public List<CollaboratorRepresentation> toRepresentationList(List<DomainCollaborator> domainList)
    {
    	if (domainList == null) {

			return null;
		}
    	
    	List<CollaboratorRepresentation> representationList = new ArrayList<CollaboratorRepresentation>();
    	for (DomainCollaborator domainCollaborator : domainList) {

			representationList.add(toOneRepresentation(domainCollaborator));
		}
    	return representationList;
    }
    
    public List<DomainCollaborator> convertListRepresentationToListDomainList(List<CollaboratorRepresentation> representationList) throws DomainException
    {
    	if (representationList == null) {

			return null;
		}

		List<DomainCollaborator> listDomain = new ArrayList<DomainCollaborator>();

		for (CollaboratorRepresentation CollaboratorRepresentation : representationList) {

			listDomain.add(toOneDomain(CollaboratorRepresentation));
		}

		return listDomain;
    }
    
    public List<CollaboratorRepresentation> convertListDomainListToListRepresentation(List<DomainCollaborator> domainList) throws DomainException
    {
    	if (domainList == null) {

			return null;
		}

		List<CollaboratorRepresentation> listRepresentation = new ArrayList<CollaboratorRepresentation>();

		for (DomainCollaborator domainCollaborator : domainList) {

			listRepresentation.add(toOneRepresentation(domainCollaborator));
		}

		return listRepresentation;
    }
}
