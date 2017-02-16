package com.opengroup.res.controllers;

import com.opengroup.res.core.CollaboratorServices;
import com.opengroup.res.core.domain.DomainCollaborator;
import com.opengroup.res.core.domain.DomainException;
//import com.opengroup.res.core.impl.CollaboratorServicesImpl;
//import com.opengroup.res.core.impl.mappers.CollaboratorMapper;
import com.opengroup.res.mappers.CollaboratorRepresentationMapper;
import com.opengroup.res.model.CollaboratorRepresentation;
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
public class CollaboratorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CollaboratorController.class);

    @Autowired
    private CollaboratorServices collaboratorServices;

//    @Autowired
//    private CollaboratorServicesImpl collaboratorServicesImpl;
    @Autowired
    private CollaboratorRepresentationMapper collaboratorRepresentationMapper;
    
//    @Autowired
//    private CollaboratorMapper collaboratorMapper;

    /**
     *
     * @return
     * @throws DomainException
     */
    @RequestMapping(value = "/collaborators", method = RequestMethod.GET) // list des collaborator
    public Set<CollaboratorRepresentation> list() throws FrontException {
        Set<CollaboratorRepresentation> collaborators;
        try {
        	collaborators = new HashSet<>(collaboratorRepresentationMapper.toRepresentations(collaboratorServices.listAll()));
        } catch (DomainException e) {
            String message = "Internal error : "+ e.getMessage();
            LOGGER.error(message, e);
            throw new FrontException(message, e);
        }
        return collaborators;
    }
    
    @RequestMapping(value = "/collaborator/{id}", method = RequestMethod.GET)
	public ResponseEntity<CollaboratorRepresentation> get(@PathVariable("id") Long id) throws DomainException, FrontException {
		CollaboratorRepresentation 
		collaboratorRepresentation = 
			collaboratorRepresentationMapper.
			toOneRepresentation(collaboratorServices.findCollaborator(id));
		if (collaboratorRepresentation == null) {
			return new ResponseEntity<CollaboratorRepresentation>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<CollaboratorRepresentation>(collaboratorRepresentation, HttpStatus.OK);
		}
	}
    
    
    @RequestMapping(value = "/collaborator/", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody CollaboratorRepresentation collaboratorRepresentation, UriComponentsBuilder ucBuilder) throws DomainException, FrontException {
		if (collaboratorRepresentation.getId() != null && collaboratorServices.findCollaborator(collaboratorRepresentation.getId()) != null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else {
			
			DomainCollaborator domainCollaborator = DomainCollaborator.newInstance(
					collaboratorRepresentation.getLoginOpen(), 
					collaboratorRepresentation.getFirstName(), 
					collaboratorRepresentation.getLastName(), 
					collaboratorRepresentation.getEmailOpen(), 
					collaboratorRepresentation.getBuOpen(), 
					collaboratorRepresentation.getId()
					);
			
			collaboratorServices.createCollaborator(
					domainCollaborator.getLoginOpen(), 
					domainCollaborator.getFirstName(), 
					domainCollaborator.getLastName(), 
					domainCollaborator.getEmailOpen(), 
					domainCollaborator.getBuOpen()
					);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/collaborator/{id}").buildAndExpand(collaboratorRepresentation.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
	}
    
    @RequestMapping(value = "/collaborator/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<CollaboratorRepresentation> delete(@PathVariable("id") Long id) throws DomainException {
		CollaboratorRepresentation collaboratorRepresentation = 
				collaboratorRepresentationMapper.toOneRepresentation(collaboratorServices.findCollaborator(id));
		if (collaboratorRepresentation.getId() == null) {
			return new ResponseEntity<CollaboratorRepresentation>(HttpStatus.NOT_FOUND);
		}
		DomainCollaborator domainCollaborator = DomainCollaborator.newInstance(
				collaboratorRepresentation.getLoginOpen(), 
				collaboratorRepresentation.getFirstName(), 
				collaboratorRepresentation.getLastName(), 
				collaboratorRepresentation.getEmailOpen(), 
				collaboratorRepresentation.getBuOpen(), 
				collaboratorRepresentation.getId()
				);
		System.out.println(domainCollaborator.toString());
		collaboratorServices.deleteCollaborator(
				collaboratorRepresentation.getId(),
				collaboratorRepresentation.getLoginOpen(), 
				collaboratorRepresentation.getFirstName(), 
				collaboratorRepresentation.getLastName(), 
				collaboratorRepresentation.getEmailOpen(), 
				collaboratorRepresentation.getBuOpen()
							);
		return new ResponseEntity<CollaboratorRepresentation>(HttpStatus.NO_CONTENT);
	}
    
    @RequestMapping(value = "/collaborator/{id}", method = RequestMethod.PUT)
	public ResponseEntity<CollaboratorRepresentation> update(@PathVariable("id") Long id, @RequestBody CollaboratorRepresentation collaboratorRepresentation) throws DomainException {
		CollaboratorRepresentation currentCollaboratorRepresentation = 
				collaboratorRepresentationMapper.toOneRepresentation(collaboratorServices.findCollaborator(id));

		if (currentCollaboratorRepresentation.getId() == null) {
			return new ResponseEntity<CollaboratorRepresentation>(HttpStatus.NOT_FOUND);
		} 
		else 
		{
			DomainCollaborator domainCollaborator = DomainCollaborator.newInstance(
					collaboratorRepresentation.getLoginOpen(), 
					collaboratorRepresentation.getFirstName(), 
					collaboratorRepresentation.getLastName(), 
					collaboratorRepresentation.getEmailOpen(), 
					collaboratorRepresentation.getBuOpen(), 
					collaboratorRepresentation.getId()
					);
			System.out.println(domainCollaborator.toString());
			collaboratorServices.updateCollaborator(
					collaboratorRepresentation.getId(),
					collaboratorRepresentation.getLoginOpen(), 
					collaboratorRepresentation.getFirstName(), 
					collaboratorRepresentation.getLastName(), 
					collaboratorRepresentation.getEmailOpen(), 
					collaboratorRepresentation.getBuOpen()
						);
			currentCollaboratorRepresentation = 
					collaboratorRepresentationMapper.toOneRepresentation(collaboratorServices.findCollaborator(currentCollaboratorRepresentation.getId()));
			return new ResponseEntity<CollaboratorRepresentation>(currentCollaboratorRepresentation, HttpStatus.OK);
		}
	}
    
}
