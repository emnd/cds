package com.opengroup.res.core.impl.mappers;

import com.opengroup.res.core.domain.DomainCollaborator;
import com.opengroup.res.core.domain.DomainException;
//import com.opengroup.res.core.domain.DomainParameter;
//import com.opengroup.res.jpa.entities.Parameter;
//import com.opengroup.res.jpa.entities.AuditState;
import com.opengroup.res.jpa.entities.Collaborator;
import com.opengroup.res.util.AbstractDomainMapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
//import java.util.Date;
//import org.springframework.beans.factory.annotation.Autowired;

/**
 * A parameter mapper
 */
@Component
public class CollaboratorMapper extends AbstractDomainMapper<DomainCollaborator, Collaborator> {

    @Override
    public DomainCollaborator toOneDomain(Collaborator entity) throws DomainException {
        Long id = entity.getId();
        String loginOpen = entity.getLoginOpen();
        String firstName = entity.getFirstName();
        String lastName = entity.getLastName();
        String emailOpen = entity.getEmailOpen();
        String buOpen = entity.getBu();



        return new DomainCollaborator(loginOpen,firstName,lastName,emailOpen, buOpen, id);
    }

    @Override
    public Collaborator toOneEntity(DomainCollaborator domain) {
        Collaborator collaborator = new Collaborator(domain.getLoginOpen().toString(), domain.getFirstName().toString(), domain.getLastName().toString(), domain.getEmailOpen().toString(), domain.getBuOpen().toString());
        collaborator.setId(domain.getId());
        return collaborator;
    }
    
    public List<Collaborator> convertDomainListToEntityList(List<DomainCollaborator> listDomain) {

		if (listDomain == null) {

			return null;
		}

		List<Collaborator> listEntity = new ArrayList<Collaborator>();

		for (DomainCollaborator domainCollaborator : listDomain) {

			listEntity.add(toOneEntity(domainCollaborator));
		}

		return listEntity;
	}
    
    
    public List<DomainCollaborator> convertEntityListToDomainList(List<Collaborator> listEntity) throws DomainException {

		if (listEntity == null) {

			return null;
		}

		List<DomainCollaborator> listDomain = new ArrayList<DomainCollaborator>();

		for (Collaborator collaborator : listEntity) {

			listDomain.add(toOneDomain(collaborator));
		}

		return listDomain;
	}
}
