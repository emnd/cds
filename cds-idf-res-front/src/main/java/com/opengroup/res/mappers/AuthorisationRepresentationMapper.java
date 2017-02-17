package com.opengroup.res.mappers;

import com.opengroup.res.core.domain.DomainAutorisation;
import com.opengroup.res.core.domain.DomainException;

import com.opengroup.res.util.mappers.AbstractFrontMapper;
import com.opengroup.res.model.AuthorisationRepresentation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Parameter representation mapper
 *
 * @author Open group
 * @since 1.0.0
 */
@Component
public class AuthorisationRepresentationMapper extends AbstractFrontMapper<DomainAutorisation, AuthorisationRepresentation> {
	
	@Autowired CollaboratorRepresentationMapper collaboratorRepresentationMapper;
	@Autowired RequestRepresentationMapper requestRepresentationMapper;
	@Autowired ProjectRepresentationMapper projectRepresentationMapper;

    @Override
    public DomainAutorisation toOneDomain(AuthorisationRepresentation representation) throws DomainException {
        return new DomainAutorisation(
        		collaboratorRepresentationMapper.toOneDomain(representation.getCollaborator()), 
        		requestRepresentationMapper.toOneDomain(representation.getRequest()), 
        		representation.getPeriodStart(), 
        		representation.getPeriodEnd(), 
        		projectRepresentationMapper.toOneDomain(representation.getProject()), 
        		representation.isEquipment(), 
        		representation.getMotive(), 
        		representation.getStatus(),
        		representation.getId()
        		);
    }

    @Override
    public AuthorisationRepresentation toOneRepresentation(DomainAutorisation domain){
    	
        AuthorisationRepresentation authorisationRepresentation = new AuthorisationRepresentation();
        authorisationRepresentation.setId(domain.getId());
        authorisationRepresentation.setCollaborator(collaboratorRepresentationMapper.toOneRepresentation(domain.getDomainCollaborator()));
        authorisationRepresentation.setRequest(requestRepresentationMapper.toOneRepresentation(domain.getDomainRequest()));
        authorisationRepresentation.setProject(projectRepresentationMapper.toOneRepresentation(domain.getDomainProject()));
        authorisationRepresentation.setPeriodStart(domain.getPeriodStart());
        authorisationRepresentation.setPeriodEnd(domain.getPeriodEnd());
        authorisationRepresentation.setMotive(domain.getMotive());
        authorisationRepresentation.setStatus(domain.getStatus());
        authorisationRepresentation.setEquipment(domain.isEquipement());
        
        return authorisationRepresentation;
    }
    
    public List<DomainAutorisation> convertListRepresentationToListDomainList(List<AuthorisationRepresentation> representationList) throws DomainException
    {
    	if (representationList == null) {

			return null;
		}

		List<DomainAutorisation> listDomain = new ArrayList<DomainAutorisation>();

		for (AuthorisationRepresentation authorisationRepresentation : representationList) {

			listDomain.add(toOneDomain(authorisationRepresentation));
		}

		return listDomain;
    }
    
    public List<AuthorisationRepresentation> convertListDomainListToListRepresentation(List<DomainAutorisation> domainList) throws DomainException
    {
    	if (domainList == null) {

			return null;
		}

		List<AuthorisationRepresentation> listRepresentation = new ArrayList<AuthorisationRepresentation>();

		for (DomainAutorisation domainAutorisation : domainList) {

			listRepresentation.add(toOneRepresentation(domainAutorisation));
		}

		return listRepresentation;
    }
}
