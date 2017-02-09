package com.opengroup.res.core.impl.mappers;

import com.opengroup.res.core.domain.DomainException;
//import com.opengroup.res.core.domain.DomainParameter;
import com.opengroup.res.core.domain.DomainRequest;
import com.opengroup.res.jpa.entities.Request;
//import com.opengroup.res.jpa.entities.Authorisation;
import com.opengroup.res.util.AbstractDomainMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;

/**
 * A Request mapper
 */
@Component
public class RequestMapper extends AbstractDomainMapper<DomainRequest, Request> {

    @Override
    public DomainRequest toOneDomain(Request entity) throws DomainException {
        Long id = entity.getId();
        String applicant = entity.getApplicant();
        String decider = entity.getDecider();
        Date requestDate = entity.getRequestDate();
        Date replyDate = entity.getReplyDate();
       // List<Authorisation> authorisationList =

        return new DomainRequest(applicant,decider, requestDate, replyDate, id);
    }

    @Override
    public Request toOneEntity(DomainRequest domain) {
        Request request = new Request(domain.getApplicant(), domain.getDecider(), domain.getRequestDate(), domain.getReplyDate() );
        request.setId(domain.getId());
        return request;
    }
    
    public List<Request> convertDomainListToEntityList(List<DomainRequest> listDomain) {

		if (listDomain == null) {

			return null;
		}

		List<Request> listEntity = new ArrayList<Request>();

		for (DomainRequest domainRequest : listDomain) {

			listEntity.add(toOneEntity(domainRequest));
		}

		return listEntity;
	}
    
    
    public List<DomainRequest> convertEntityListToDomainList(List<Request> listEntity) throws DomainException {

		if (listEntity == null) {

			return null;
		}

		List<DomainRequest> listDomain = new ArrayList<DomainRequest>();

		for (Request request : listEntity) {

			listDomain.add(toOneDomain(request));
		}

		return listDomain;
	}
}
