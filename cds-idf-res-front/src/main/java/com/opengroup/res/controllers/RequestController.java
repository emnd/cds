package com.opengroup.res.controllers;

import com.opengroup.res.core.RequestServices;
import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.core.domain.DomainRequest;
import com.opengroup.res.mappers.RequestRepresentationMapper;
import com.opengroup.res.model.RequestRepresentation;
import com.opengroup.res.util.FrontException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashSet;
import java.util.Set;

/**
 * A REST Controller which provides API to manage application parameters
 *
 * @author Open group
 * @since 1.0.0
 */
@RestController
public class RequestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestController.class);

    @Autowired
    private RequestServices requestServices;

    @Autowired
    private RequestRepresentationMapper requestRepresentationMapper;

    /**
     *
     * @return
     * @throws DomainException
     */
    @RequestMapping(value = "/requests", method = RequestMethod.GET)
    public Set<RequestRepresentation> list() throws FrontException {
        Set<RequestRepresentation> requests;
        try {
        	requests = new HashSet<>(requestRepresentationMapper.toRepresentations(requestServices.listAll()));
        } catch (DomainException e) {
            String message = "Internal error : "+ e.getMessage();
            LOGGER.error(message, e);
            throw new FrontException(message, e);
        }
        return requests;
    }
    
    @RequestMapping(value = "/request/{id}", method = RequestMethod.GET)
	public ResponseEntity<RequestRepresentation> get(@PathVariable("id") Long id) throws DomainException, FrontException {
		RequestRepresentation 
		requestRepresentation = requestRepresentationMapper.toOneRepresentation(requestServices.findRequest(id));
		if (requestRepresentation == null) {
			return new ResponseEntity<RequestRepresentation>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<RequestRepresentation>(requestRepresentation, HttpStatus.OK);
		}
	}
    
    @RequestMapping(value = "/request/", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody RequestRepresentation requestRepresentation, UriComponentsBuilder ucBuilder) throws DomainException, FrontException {
		if (requestRepresentation.getId() != null && requestServices.findRequest(requestRepresentation.getId()) != null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else {
			
			DomainRequest domainRequest = DomainRequest.newInstance(
					requestRepresentation.getApplicant(), 
					requestRepresentation.getDecider(), 
					requestRepresentation.getRequestDate(), 
					requestRepresentation.getReplyDate(), 
					requestRepresentation.getApplicantEmail(),
					requestRepresentation.getId()
					);
			
			requestServices.createRequest(domainRequest.getApplicant(), domainRequest.getDecider(), domainRequest.getRequestDate(), domainRequest.getReplyDate(),domainRequest.getApplicantEmail());
			
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/request/{id}").buildAndExpand(requestRepresentation.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
	}
    
    @RequestMapping(value = "/request/{id}", method = RequestMethod.DELETE)
   	public ResponseEntity<RequestRepresentation> delete(@PathVariable("id") Long id) throws DomainException {
   		RequestRepresentation requestRepresentation = 
   				requestRepresentationMapper.toOneRepresentation(requestServices.findRequest(id));
   		if (requestRepresentation.getId() == null) {
   			return new ResponseEntity<RequestRepresentation>(HttpStatus.NOT_FOUND);
   		}
   		DomainRequest domainRequest = DomainRequest.newInstance(
   				requestRepresentation.getApplicant(), 
   				requestRepresentation.getDecider(), 
   				requestRepresentation.getRequestDate(), 
   				requestRepresentation.getReplyDate(),
   				requestRepresentation.getApplicantEmail(),
   				requestRepresentation.getId()
   				);
   		System.out.println(domainRequest.toString());
   		requestServices.deleteRequest(
   				requestRepresentation.getId(),
   				requestRepresentation.getApplicant(), 
   				requestRepresentation.getDecider(), 
   				requestRepresentation.getRequestDate(), 
   				requestRepresentation.getReplyDate(),
   				requestRepresentation.getApplicantEmail()
   				);
   		return new ResponseEntity<RequestRepresentation>(HttpStatus.NO_CONTENT);
   	}
    
    @RequestMapping(value = "/request/{id}", method = RequestMethod.PUT)
	public ResponseEntity<RequestRepresentation> update(@PathVariable("id") Long id, @RequestBody RequestRepresentation requestRepresentation) throws DomainException {
		RequestRepresentation currentRequestRepresentation = 
				requestRepresentationMapper.toOneRepresentation(requestServices.findRequest(id));

		if (currentRequestRepresentation.getId() == null) {
			return new ResponseEntity<RequestRepresentation>(HttpStatus.NOT_FOUND);
		} 
		else 
		{
			DomainRequest domainRequest = DomainRequest.newInstance(
					requestRepresentation.getApplicant(), 
	   				requestRepresentation.getDecider(), 
	   				requestRepresentation.getRequestDate(), 
	   				requestRepresentation.getReplyDate(),
	   				requestRepresentation.getApplicantEmail(),
	   				requestRepresentation.getId()
					);
			System.out.println(domainRequest.toString());
			requestServices.updateRequest(
					requestRepresentation.getId(),
	   				requestRepresentation.getApplicant(), 
	   				requestRepresentation.getDecider(), 
	   				requestRepresentation.getRequestDate(), 
	   				requestRepresentation.getReplyDate(),
	   				requestRepresentation.getApplicantEmail()
						);
			currentRequestRepresentation = 
					requestRepresentationMapper.toOneRepresentation(requestServices.findRequest(currentRequestRepresentation.getId()));
			return new ResponseEntity<RequestRepresentation>(currentRequestRepresentation, HttpStatus.OK);
		}
	}
}
