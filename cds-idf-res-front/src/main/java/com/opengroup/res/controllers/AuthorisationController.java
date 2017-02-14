package com.opengroup.res.controllers;

import com.opengroup.res.core.AutorisationServices;
import com.opengroup.res.core.CollaboratorServices;
import com.opengroup.res.core.ProjectServices;
import com.opengroup.res.core.RequestServices;
import com.opengroup.res.core.domain.DomainAutorisation;
import com.opengroup.res.core.domain.DomainCollaborator;
import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.core.domain.DomainProject;
import com.opengroup.res.core.domain.DomainRequest;
import com.opengroup.res.core.impl.mappers.CollaboratorMapper;
import com.opengroup.res.core.impl.mappers.ProjectMapper;
import com.opengroup.res.core.impl.mappers.RequestMapper;
import com.opengroup.res.mappers.AuthorisationRepresentationMapper;
import com.opengroup.res.mappers.CollaboratorRepresentationMapper;
import com.opengroup.res.mappers.ProjectRepresentationMapper;
import com.opengroup.res.mappers.RequestRepresentationMapper;
import com.opengroup.res.model.AuthorisationRepresentation;
import com.opengroup.res.model.CollaboratorRepresentation;
import com.opengroup.res.model.ProjectRepresentation;
import com.opengroup.res.model.RequestRepresentation;
import com.opengroup.res.util.FrontException;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpHeaders;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * A REST Controller which provides API to manage application parameters
 *
 * @author Open group
 * @since 1.0.0
 */
@RestController
public class AuthorisationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorisationController.class);

    @Autowired
    private AutorisationServices autorisationServices;
    
    @Autowired
    private CollaboratorServices collaboratorServices;
    
    @Autowired
    private ProjectServices projectServices;
    
    @Autowired
    private RequestServices requestServices;

    @Autowired
    private AuthorisationRepresentationMapper authorisationRepresentationMapper;
    
    @Autowired
    private CollaboratorRepresentationMapper collaboratorRepresentationMapper;
    
    @Autowired
    private CollaboratorMapper collaboratorMapper;
    
    @Autowired
    private RequestRepresentationMapper requestRepresentationMapper;
    
    @Autowired
    private RequestMapper requestMapper;
    
    @Autowired
    private ProjectRepresentationMapper projectRepresentationMapper;
    
    @Autowired
    private ProjectMapper projectMapper;

    /**
     *
     * @return
     * @throws DomainException
     */
    @RequestMapping(value = "/authorisations", method = RequestMethod.GET)
    public Set<AuthorisationRepresentation> list() throws FrontException {
        Set<AuthorisationRepresentation> authorisations;
        try {
            authorisations = new HashSet<>(authorisationRepresentationMapper.toRepresentations(autorisationServices.listAll()));
        } catch (DomainException e) {
            String message = "Internal error : "+ e.getMessage();
            LOGGER.error(message, e);
            throw new FrontException(message, e);
        }
        return authorisations;
    }
    
    @RequestMapping(value = "/authorisation/{id}", method = RequestMethod.GET)
	public ResponseEntity<AuthorisationRepresentation> get(@PathVariable("id") Long id) throws DomainException, FrontException {
		AuthorisationRepresentation 
		authorisationRepresentation = authorisationRepresentationMapper.toOneRepresentation(autorisationServices.findAutorisation(id));
		if (authorisationRepresentation == null) {
			return new ResponseEntity<AuthorisationRepresentation>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<AuthorisationRepresentation>(authorisationRepresentation, HttpStatus.OK);
		}
	}
    
    
    @RequestMapping(value = "/authorisation/", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody AuthorisationRepresentation authorisationRepresentation, UriComponentsBuilder ucBuilder) throws DomainException, FrontException {
		if (authorisationRepresentation.getId() != null && autorisationServices.findAutorisation(authorisationRepresentation.getId()) != null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else {
			
			DomainCollaborator domainCollaborator= collaboratorRepresentationMapper.toOneDomain(authorisationRepresentation.getCollaborator());
			DomainProject domainProject = projectRepresentationMapper.toOneDomain(authorisationRepresentation.getProject());
			DomainRequest domainRequest = requestRepresentationMapper.toOneDomain(authorisationRepresentation.getRequest());
			Date periodStart = authorisationRepresentation.getPeriodStart();
			Date periodEnd = authorisationRepresentation.getPeriodEnd();
			String motive = authorisationRepresentation.getMotive();
			String status = authorisationRepresentation.getStatus();
			boolean equipement = authorisationRepresentation.isEquipment();
			Long id = authorisationRepresentation.getId();
			
			DomainAutorisation domainAutorisation = DomainAutorisation.newInstance(domainCollaborator,
					domainRequest,
					periodStart, 
					periodEnd, 
					domainProject, 
					equipement,
					motive, 
					status, 
					id);
			System.out.println(domainAutorisation.toString());
			autorisationServices.createAutorisation(domainCollaborator,
					domainRequest, 
					periodStart, 
					periodEnd, 
					equipement, 
					motive, 
					"Attente", 
					domainProject);
	
			
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/authorisation/{id}").buildAndExpand(authorisationRepresentation.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
	}
}
