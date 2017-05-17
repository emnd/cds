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
import com.opengroup.res.organization.UserServices;
import com.opengroup.res.organization.domain.DomainEmployee;
import com.opengroup.res.organization.domain.DomainRole;
import com.opengroup.res.organization.domain.DomainUser;
import com.opengroup.res.util.FrontException;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpHeaders;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
    
    @Autowired
    private UserServices userServices;
    
    String  userName,userEmail;
    // le 17-05-2017
	Set<String> userRoles;

	// le 17-05-2017
    /**
     *
     * @return
     * @throws DomainException
     */
    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public List<String> master(Principal principal) { // listes des infos sur l'utilisateur courant
    	try {
            userServices.get(principal);
            //System.out.println("principal");
            //System.out.println(principal.getName());
            DomainEmployee domainEmployee = userServices.get(principal.getName());
            //System.out.println("domainEmployee");
            userName = principal.getName(); // userName ou login  de l'utilisateur
            //System.out.println(domainEmployee.getCoordinate().getEmail());
            userEmail = domainEmployee.getCoordinate().getEmail(); // userEmail ou email de l'utilisateur courant
			// le 17-05-2017
			DomainUser domainUser = userServices.get(principal);

			Set<DomainRole> domainRole = domainUser.getRoles();
			for(DomainRole dmR : domainRole)
			{
				System.out.println("Rôle : "+dmR.name());
				//userRoles.add(dmR.name());

			}
			//domainUser = DomainUser.newInstance(domainEmployee.getIdentity(),domainEmployee.getJob(),domainEmployee.getCoordinate(),(List<String>)userRoles);
			//System.out.println("taille des roles : "+domainUser.getRoles().size());
			// le 17-05-2017
            List<String> userInfo = new ArrayList<String>();
            userInfo.add(userName);
            userInfo.add(userEmail);
            return userInfo;
        } catch (Exception e) {
            LOGGER.error("A problem occurs while controlling the authenticated user", e);
            return null;
        }
    }
    
   @Secured("ROLE_CDSMANAGER")
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
			
			// debut pour l'envoi de la notification
			try {
				autorisationServices.sendNotificationDemander(domainAutorisation.getDomainCollaborator().getFirstName(),
				domainAutorisation.getDomainCollaborator().getLastName(), 
				domainAutorisation.getDomainCollaborator().getEmailOpen(), 
				domainAutorisation.getDomainRequest().getApplicantEmail()); // pour le demandeur et pour le collaborateur
			}
			catch (MailException e) {
				System.out.println("Error mail: " + e.getMessage());
			}
			
			try {
				autorisationServices.sendNotificationDecider(domainAutorisation.getDomainCollaborator().getFirstName(),
				domainAutorisation.getDomainCollaborator().getLastName(), 
				domainAutorisation.getDomainCollaborator().getEmailOpen(), 
				"moussa.ndiaye@open-groupe.com"); // pour le decideur à l'instant initialisé a "moussa.ndiaye@open-groupe.com"
			}
			catch (MailException e) {
				System.out.println("Error mail: " + e.getMessage());
			}
			//fin envoi de mail
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/authorisation/{id}").buildAndExpand(authorisationRepresentation.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
	}
    
    @RequestMapping(value = "/authorisation/{id}", method = RequestMethod.DELETE)
   	public ResponseEntity<AuthorisationRepresentation> delete(@PathVariable("id") Long id) throws DomainException {
   		AuthorisationRepresentation authorisationRepresentation = 
   				authorisationRepresentationMapper.toOneRepresentation(autorisationServices.findAutorisation(id));
   		if (authorisationRepresentation.getId() == null) {
   			return new ResponseEntity<AuthorisationRepresentation>(HttpStatus.NOT_FOUND);
   		}
   		
   		DomainCollaborator domainCollaborator= collaboratorRepresentationMapper.toOneDomain(authorisationRepresentation.getCollaborator());
		DomainProject domainProject = projectRepresentationMapper.toOneDomain(authorisationRepresentation.getProject());
		DomainRequest domainRequest = requestRepresentationMapper.toOneDomain(authorisationRepresentation.getRequest());
		Date periodStart = authorisationRepresentation.getPeriodStart();
		Date periodEnd = authorisationRepresentation.getPeriodEnd();
		String motive = authorisationRepresentation.getMotive();
		String status = authorisationRepresentation.getStatus();
		boolean equipement = authorisationRepresentation.isEquipment();
		id = authorisationRepresentation.getId();
		
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
   		autorisationServices.deleteAutorisation(
   				id,
   				domainCollaborator,
				domainRequest,
				periodStart, 
				periodEnd, 
				equipement,
				motive, 
				status,
				domainProject
   				);
   		return new ResponseEntity<AuthorisationRepresentation>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value = "/authorisation/{id}", method = RequestMethod.PUT)
	public ResponseEntity<AuthorisationRepresentation> update(@PathVariable("id") Long id, @RequestBody AuthorisationRepresentation authorisationRepresentation) throws DomainException {
		AuthorisationRepresentation currentAuthorisationRepresentation = 
				authorisationRepresentationMapper.toOneRepresentation(autorisationServices.findAutorisation(id));

		if (currentAuthorisationRepresentation.getId() == null) {
			return new ResponseEntity<AuthorisationRepresentation>(HttpStatus.NOT_FOUND);
		} 
		else 
		{
			DomainCollaborator domainCollaborator= collaboratorRepresentationMapper.toOneDomain(authorisationRepresentation.getCollaborator());
			DomainProject domainProject = projectRepresentationMapper.toOneDomain(authorisationRepresentation.getProject());
			DomainRequest domainRequest = requestRepresentationMapper.toOneDomain(authorisationRepresentation.getRequest());
			Date periodStart = authorisationRepresentation.getPeriodStart();
			Date periodEnd = authorisationRepresentation.getPeriodEnd();
			String motive = authorisationRepresentation.getMotive();
			String status = authorisationRepresentation.getStatus();
			boolean equipement = authorisationRepresentation.isEquipment();
			id = authorisationRepresentation.getId();
			
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
   		autorisationServices.updateAutorisation(
   				id,
   				domainCollaborator,
				domainRequest,
				periodStart, 
				periodEnd, 
				equipement,
				motive, 
				status,
				domainProject
   				);
			currentAuthorisationRepresentation = 
					authorisationRepresentationMapper.toOneRepresentation(autorisationServices.findAutorisation(currentAuthorisationRepresentation.getId()));
			return new ResponseEntity<AuthorisationRepresentation>(currentAuthorisationRepresentation, HttpStatus.OK);
			
			
		}
	}
    
    @RequestMapping(value="/requestList/{applicant}", method = RequestMethod.GET)
    public List<AuthorisationRepresentation> getRequestList(@PathVariable String applicant) throws DomainException, FrontException {  //TODO Catch the exception and change the response status
        List<DomainAutorisation> authorisations = autorisationServices.authorisationHistory(applicant);
        return authorisationRepresentationMapper.convertListDomainListToListRepresentation(authorisations);
    }
    
    //@Secured("ROLE_CDSMANAGER")
    @RequestMapping(value="/todayActive", method = RequestMethod.GET)
    @ResponseBody
    public List<AuthorisationRepresentation> todayActive() throws DomainException,FrontException {
        List<DomainAutorisation> authorisationsToday = autorisationServices.activeAuthorisations(new Date());
        return authorisationRepresentationMapper.convertListDomainListToListRepresentation(authorisationsToday);
    }
    
    @RequestMapping(value = "/Attente/{id}:{decider}", method = RequestMethod.PUT) // le statut en attente
	public void updateStatusEnAttente(@PathVariable Long id, @PathVariable String decider) throws DomainException,FrontException {
		autorisationServices.updateStatus(id, "Attente",decider);
		DomainAutorisation domainAutorisation = autorisationServices.findAutorisation(id);
		try{
			autorisationServices.sendNotificationDemanderWaited(domainAutorisation.getDomainCollaborator().getFirstName(),
					domainAutorisation.getDomainCollaborator().getLastName(), 
					domainAutorisation.getDomainCollaborator().getEmailOpen(), 
					domainAutorisation.getDomainRequest().getApplicantEmail()
					);
		}
		catch (MailException e) {
			System.out.println("Error mail: " + e.getMessage());
		}
		
	}
    
    
    @RequestMapping(value = "/Acceptée/{id}:{decider}", method = RequestMethod.PUT) // le statut en acceptée
	public void updateStatusAccepter(@PathVariable Long id, @PathVariable String decider) throws DomainException,FrontException {
		autorisationServices.updateStatus(id, "Acceptée",decider);
		// pour l'envoi de la notification
		DomainAutorisation domainAutorisation = autorisationServices.findAutorisation(id);
		try{
			autorisationServices.sendNotificationDemanderAccepted(domainAutorisation.getDomainCollaborator().getFirstName(),
					domainAutorisation.getDomainCollaborator().getLastName(), 
					domainAutorisation.getDomainCollaborator().getEmailOpen(), 
					domainAutorisation.getDomainRequest().getApplicantEmail()
					);
		}
		catch (MailException e) {
			System.out.println("Error mail: " + e.getMessage());
		}
		
	}
    
    @RequestMapping(value = "/Refusée/{id}:{decider}", method = RequestMethod.PUT) // le statut en refusée
	public void updateStatusRefuser(@PathVariable Long id, @PathVariable String decider)throws DomainException,FrontException {
		autorisationServices.updateStatus(id, "Refusée",decider);
		// pour l'envoi de la notification
		DomainAutorisation domainAutorisation = autorisationServices.findAutorisation(id);
		try{
			autorisationServices.sendNotificationDemanderRefused(domainAutorisation.getDomainCollaborator().getFirstName(),
					domainAutorisation.getDomainCollaborator().getLastName(), 
					domainAutorisation.getDomainCollaborator().getEmailOpen(), 
					domainAutorisation.getDomainRequest().getApplicantEmail()
					);
		}
		catch (MailException e) {
			System.out.println("Error mail: " + e.getMessage());
		}
		
	}
    
    
    @RequestMapping(value="/requestListCollaborator/{loginOpen}:{emailOpen}", method = RequestMethod.GET) // Liste des demandes ou des autorisation pour le collaborator de loginOpen et emailOpen
    public List<AuthorisationRepresentation> getRequestListCollaborator(@PathVariable String loginOpen, @PathVariable String emailOpen) throws DomainException, FrontException {  //TODO Catch the exception and change the response status
        List<DomainAutorisation> authorisations = autorisationServices.findAuthorisationByCollaborator(loginOpen, emailOpen);
        return authorisationRepresentationMapper.convertListDomainListToListRepresentation(authorisations);
    }
    
    @RequestMapping(value="/requestListProject/{projectName}", method = RequestMethod.GET) // Liste des demandes ou des autorisation pour un projet de nom projectName en parametre
    public List<AuthorisationRepresentation> extendProject(@PathVariable String projectName) throws DomainException, FrontException {  //TODO Catch the exception and change the response status
        List<DomainAutorisation> authorisations = autorisationServices.findAuthorisationByProject(projectName);
        return authorisationRepresentationMapper.convertListDomainListToListRepresentation(authorisations);
    }
    

}
