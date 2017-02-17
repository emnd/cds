package com.opengroup.res.mappers;

import com.opengroup.res.core.domain.DomainException;
//import com.opengroup.res.core.domain.DomainProject;
import com.opengroup.res.core.domain.DomainRequest;
//import com.opengroup.res.util.FrontException;
import com.opengroup.res.util.mappers.AbstractFrontMapper;
//import com.opengroup.res.model.ProjectRepresentation;
import com.opengroup.res.model.RequestRepresentation;

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
public class RequestRepresentationMapper extends AbstractFrontMapper<DomainRequest, RequestRepresentation> {

    @Override
    public DomainRequest toOneDomain(RequestRepresentation representation) throws DomainException {
    	 return new DomainRequest(representation.getApplicant(), representation.getDecider(), representation.getRequestDate(), representation.getReplyDate(),representation.getId());
    }

    @Override
    public RequestRepresentation toOneRepresentation(DomainRequest domain){
    	RequestRepresentation requestRepresentation = new RequestRepresentation();
    	requestRepresentation.setId(domain.getId());
    	requestRepresentation.setApplicant(domain.getApplicant());
    	requestRepresentation.setDecider(domain.getDecider());
    	requestRepresentation.setRequestDate(domain.getRequestDate());
    	requestRepresentation.setReplyDate(domain.getReplyDate());
        return requestRepresentation;
    }
    
    public List<RequestRepresentation> toRepresentationList(List<DomainRequest> domainList)
    {
    	if (domainList == null) {

			return null;
		}
    	
    	List<RequestRepresentation> representationList = new ArrayList<RequestRepresentation>();
    	for (DomainRequest domainRequest : domainList) {

			representationList.add(toOneRepresentation(domainRequest));
		}
    	return representationList;
    }
    
    public List<DomainRequest> convertListRepresentationToListDomainList(List<RequestRepresentation> representationList) throws DomainException
    {
    	if (representationList == null) {

			return null;
		}

		List<DomainRequest> listDomain = new ArrayList<DomainRequest>();

		for (RequestRepresentation RequestRepresentation : representationList) {

			listDomain.add(toOneDomain(RequestRepresentation));
		}

		return listDomain;
    }
    
    public List<RequestRepresentation> convertListDomainListToListRepresentation(List<DomainRequest> domainList) throws DomainException
    {
    	if (domainList == null) {

			return null;
		}

		List<RequestRepresentation> listRepresentation = new ArrayList<RequestRepresentation>();

		for (DomainRequest domainRequest : domainList) {

			listRepresentation.add(toOneRepresentation(domainRequest));
		}

		return listRepresentation;
    }
}
