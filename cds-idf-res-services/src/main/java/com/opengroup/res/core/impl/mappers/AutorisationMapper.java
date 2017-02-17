package com.opengroup.res.core.impl.mappers;

import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.core.domain.DomainProject;
import com.opengroup.res.core.domain.DomainRequest;
import com.opengroup.res.core.domain.DomainAutorisation;
import com.opengroup.res.core.domain.DomainCollaborator;
import com.opengroup.res.jpa.entities.Authorisation;
//import com.opengroup.res.jpa.entities.Request;
//import com.opengroup.res.jpa.entities.Collaborator;
import com.opengroup.res.util.AbstractDomainMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opengroup.res.core.impl.mappers.CollaboratorMapper;
import com.opengroup.res.core.impl.mappers.RequestMapper;
import com.opengroup.res.core.impl.mappers.ProjectMapper;

import org.springframework.beans.factory.annotation.Autowired;



/**
 * A autorisation mapper
 */
@Component
public class AutorisationMapper extends AbstractDomainMapper<DomainAutorisation, Authorisation> {

    @Autowired
    CollaboratorMapper collaboratorMapper;

    @Autowired
    RequestMapper requestMapper;

    @Autowired
    ProjectMapper projectMapper;

    @Override
    public DomainAutorisation toOneDomain(Authorisation entity) throws DomainException {
        Long id = entity.getId();
        boolean equipement = entity.isEquipment();
        Date periodStart = entity.getPeriodStart();
        Date periodEnd = entity.getPeriodEnd();
        String status = entity.getStatus();
        String motive = entity.getMotive();
        DomainCollaborator domainCollaborator = collaboratorMapper.toOneDomain(entity.getCollaborator());
        DomainProject domainProject = projectMapper.toOneDomain(entity.getProject());
        DomainRequest domainRequest = requestMapper.toOneDomain(entity.getRequest());

        return new DomainAutorisation(domainCollaborator, domainRequest, periodStart, periodEnd, domainProject, equipement, motive, status,id);

    }

    @Override
    public Authorisation toOneEntity(DomainAutorisation domain) {
        Authorisation authorisation = new Authorisation(collaboratorMapper.toOneEntity(domain.getDomainCollaborator()), requestMapper.toOneEntity(domain.getDomainRequest()), domain.getPeriodStart() , domain.getPeriodEnd(), domain.isEquipement(), domain.getMotive(), projectMapper.toOneEntity(domain.getDomainProject()) );
        authorisation.setStatus(domain.getStatus());
        authorisation.setId(domain.getId());

        return authorisation;
    }
    
    
    public List<Authorisation> convertDomainListToEntityList(List<DomainAutorisation> listDomain) {

		if (listDomain == null) {

			return null;
		}

		List<Authorisation> listEntity = new ArrayList<Authorisation>();

		for (DomainAutorisation domainAuthorisation : listDomain) {

			listEntity.add(toOneEntity(domainAuthorisation));
		}

		return listEntity;
	}
    
    
    public List<DomainAutorisation> convertEntityListToDomainList(List<Authorisation> listEntity) throws DomainException {

		if (listEntity == null) {

			return null;
		}

		List<DomainAutorisation> listDomain = new ArrayList<DomainAutorisation>();

		for (Authorisation Authorisation : listEntity) {

			listDomain.add(toOneDomain(Authorisation));
		}

		return listDomain;
	}
}
