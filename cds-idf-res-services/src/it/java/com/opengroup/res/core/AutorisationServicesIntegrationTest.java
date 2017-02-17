package com.opengroup.res.core;


import java.text.SimpleDateFormat;
//import java.util.Date;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.opengroup.res.core.AutorisationServices;
import com.opengroup.res.core.domain.DomainCollaborator;
import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.core.domain.DomainProject;
import com.opengroup.res.core.domain.DomainRequest;
import com.opengroup.res.core.impl.mappers.CollaboratorMapper;
import com.opengroup.res.core.impl.mappers.ProjectMapper;
import com.opengroup.res.core.impl.mappers.RequestMapper;
import com.opengroup.res.jpa.AuthorisationRepository;
import com.opengroup.res.jpa.CollaboratorRepository;
import com.opengroup.res.jpa.ProjectRepository;
import com.opengroup.res.jpa.RequestRepository;

/**
 * Integration test on collaborator
 *
 * @author Open groupe
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class AutorisationServicesIntegrationTest {
	
	@Autowired
    private AutorisationServices autorisationServices;

    @Autowired
    private CollaboratorRepository collaboratorRepository;
    
    @Autowired
    private CollaboratorMapper collaboratorMapper;
    
    @Autowired
    private RequestRepository requestRepository;
    
    @Autowired
    private RequestMapper requestMapper;
    
    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private ProjectMapper projectMapper;
    
    @Autowired
    private AuthorisationRepository autorisationRepository;
    
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	//@Autowired private RequestServices requestServices;
	//@Autowired private CollaboratorServices collaboratorServices;
	//@Autowired private ProjectServices projectServices;
	
  /* @Test
    @Commit
    public void testCreate() throws Exception {
    	
    	DomainCollaborator domCollabExisting = collaboratorMapper.toOneDomain(collaboratorRepository.findOne(6L));
    	System.out.println(domCollabExisting.getId());
    	DomainRequest domReqExisting = requestMapper.toOneDomain(requestRepository.findOne(6L));
    	System.out.println(domReqExisting.getId());
    	
    	DomainProject domProjExisting = projectMapper.toOneDomain(projectRepository.findOne(67L));
    	System.out.println(domProjExisting.getId());
    	autorisationServices.createAutorisation(domCollabExisting, domReqExisting, sdf.parse("08/02/2017"), sdf.parse("08/12/2018"), true, "Autorisation au CDS pour croissant", "ATTENTE", domProjExisting);
 
    } */
	
	/* @Test
	    @Commit
	    public void testCreate1() throws Exception {
	    	projectServices.createProject("PROJET ", new Date(), new Date());
	    	projectServices.createProject("PROJET 23", sdf.parse("07/02/2017"), sdf.parse("25/02/2018"));
	    	projectServices.createProject("PROJET 34", sdf.parse("20/12/2016"), sdf.parse("31/07/2017"));
	    	projectServices.createProject("PROJET 45", sdf.parse("24/12/2016"), sdf.parse("24/02/2018"));
	    } */
		
	  /*   @Test
	    @Commit
	    public void testCreate2() throws Exception {
	    	requestServices.createRequest("String applicant 3", "String decider 3", sdf.parse("07/02/2037"), sdf.parse("32/02/2037"));
	 
	    	requestServices.createRequest("String applicant 4", "String decider 4",sdf.parse("07/02/2037"), sdf.parse("34/02/2037"));
	    } */
	    
	  /*  @Test
	    @Commit
	    public void testCreate3() throws Exception {
	    	collaboratorServices.createCollaborator("String loginOpen 1", "String firstName 1", "String lastName 1", "String emailOpen 1", "String bu 1");
	 
	    	collaboratorServices.createCollaborator("String loginOpen 3", "String firstName 3", "String lastName 3", "String emailOpen 2", "String bu 2");
	    } */

   /* @Test
    @Commit
    public void testUpdate() throws Exception {
        
    	DomainCollaborator domainCollaborator1 = collaboratorMapper.toOneDomain(collaboratorRepository.findOne(3L));
    	System.out.println("domainCollaborator1   :  "+domainCollaborator1.getId());
    	DomainRequest domainRequest1 = requestMapper.toOneDomain(requestRepository.findOne(3L));
    	System.out.println("domainRequest1   :  "+domainRequest1.getId());
    	DomainProject domainProject1 = projectMapper.toOneDomain(projectRepository.findOne(60L));
    	System.out.println("domainProject1   :  "+domainProject1.getId());
    	System.out.println("idAutorisation = "+autorisationRepository.findOne(64L).getId());
    	autorisationServices.updateAutorisation(autorisationRepository.findOne(64L).getId(),domainCollaborator1, domainRequest1, sdf.parse("06/02/2017"), sdf.parse("06/12/2017"), true, "Demande d'autorisation au CDS", "ACCEPTE", domainProject1);
    	

    } */

  /* @Test
    @Commit
    public void testDelete() throws Exception {
        autorisationServices.deleteAutorisation(
        		70L,
        		collaboratorMapper.toOneDomain(collaboratorRepository.findOne(5L)), 
        		requestMapper.toOneDomain(requestRepository.findOne(5L)), 
        		sdf.parse("08/02/2017"), 
        		sdf.parse("08/12/2018"), 
        		false, 
        		"demande d'autorisation au CDS pour croissant",
        		"VALIDE", 
        		projectMapper.toOneDomain(projectRepository.findOne(66L))
        		);
    }  */


}
