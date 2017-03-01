package com.opengroup.res.core.impl;

import com.opengroup.res.core.AutorisationServices;
import com.opengroup.res.core.CollaboratorServices;
import com.opengroup.res.core.ProjectServices;
import com.opengroup.res.core.RequestServices;
import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.core.domain.DomainHistoryLog;
import com.opengroup.res.core.domain.DomainAutorisation;
import com.opengroup.res.core.impl.mappers.AutorisationMapper;
import com.opengroup.res.core.impl.mappers.CollaboratorMapper;
import com.opengroup.res.core.impl.mappers.ProjectMapper;
import com.opengroup.res.core.impl.mappers.RequestMapper;
import com.opengroup.res.jpa.HistoryLogRepository;
import com.opengroup.res.jpa.ProjectRepository;
import com.opengroup.res.jpa.RequestRepository;
import com.opengroup.res.jpa.AuthorisationRepository;
import com.opengroup.res.jpa.CollaboratorRepository;
import com.opengroup.res.jpa.entities.Authorisation;
import com.opengroup.res.jpa.entities.Collaborator;
import com.opengroup.res.jpa.entities.HistoryLog;
import com.opengroup.res.jpa.entities.Project;
import com.opengroup.res.jpa.entities.Request;
import com.opengroup.res.organization.UserServices;

import ch.qos.logback.core.net.SyslogOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.opengroup.res.core.domain.DomainCollaborator;
import com.opengroup.res.core.domain.DomainRequest;
import com.opengroup.res.core.domain.DomainProject;


/**
 * Implementation to manage Autorisation service
 *
 * @author Open groupe
 * @since 1.0.0
 */
@Service
public class AutorisationServicesImpl implements AutorisationServices {

    @Autowired
    private HistoryLogRepository historyLogRepository;

    @Autowired
    private AuthorisationRepository authorisationRepository;

    @Autowired
    private AutorisationMapper autorisationMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private CollaboratorMapper collaboratorMapper;

    @Autowired
    private RequestMapper requestMapper;
    
    @Autowired
    private RequestRepository requestRepository;
    
    @Autowired
    private CollaboratorRepository collaboratorRepository;
    
    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private ProjectServices projectServices;
    
    @Autowired
    private CollaboratorServices collaboratorServices;
    
    @Autowired
    private RequestServices requestServices;
    
    @Autowired
    private UserServices userServices;

    @Override
    @Transactional
    public void createAutorisation(DomainCollaborator domainCollaborator, DomainRequest domainRequest, Date periodStart, Date periodEnd, boolean equipement, String motive,String status, DomainProject domainProject) throws DomainException {
    	Long id = 1L;
    	Request request = requestMapper.toOneEntity(domainRequest);
    	if(domainRequest.getId()== null)
    	{
    		requestRepository.save(request);   // creation de la request s'il n'existe pas
    	}
    	Collaborator collaborator = new Collaborator();
    	Collaborator existingCollaborator = collaboratorServices.findCollaborator(domainCollaborator.getLoginOpen(), domainCollaborator.getEmailOpen()); // recherche du collaborateur en base sinon l'inserer

    	collaborator = (existingCollaborator.getId() != null) ? existingCollaborator : collaboratorMapper.toOneEntity(domainCollaborator);
    	
    	if(collaborator.getId()== null)
    	{
    		collaboratorRepository.save(collaborator);   // creation du collaborator s'il n'existe pas
    	}
    	
    	Project project = new Project();
    	Project existingProject = new Project();
    	Project existingProjectName = new Project();
    	try{
    		existingProject = 
        			projectServices.findProject(domainProject.getProjectName(), domainProject.getPeriodStart(), domainProject.getPeriodEnd()); // verification si le projet existe
    	}catch(NullPointerException e){}
    	
    	try{
    		 existingProjectName = projectServices.findProject(domainProject.getProjectName());
    	}catch(NullPointerException e){}
    	
    	if(existingProject.getIdProject() != null)
    	{
    		project = existingProject; 
    		System.out.println("projectNamePeriods trouvé :"+project.toString());
    	}else if(existingProjectName.getIdProject() != null)
    	{
    		project = existingProjectName; 
    		System.out.println("projectName trouvé :"+project.toString());
    	}else
    	{
    		project =  projectMapper.toOneEntity(domainProject);
    		
        		projectRepository.save(project);   // creation du project s'il n'existe pas
        	
    	}
    	
    	System.out.println("project :"+project.toString());
    	
    	

    	domainCollaborator = collaboratorMapper.toOneDomain(collaborator); // transformation du collaborator crée en domainCollaborator
    	domainRequest = requestMapper.toOneDomain(request);  // transformation de la request créée en domainRequest
    	domainProject = projectMapper.toOneDomain(project);  // transformation du project crée en domainProject
    	
    	DomainAutorisation domainAutorisation = DomainAutorisation.newInstance(domainCollaborator, domainRequest, periodStart, periodEnd, domainProject, equipement, motive, status,id);
        domainAutorisation.setStatus("Attente"); // initialisation du statut en attente lors de la création
        Authorisation oneEntity = autorisationMapper.toOneEntity(domainAutorisation);
		authorisationRepository.save(oneEntity);
        //logTrackParameter(now,"DEFAULT CREATION MESSAGE", DomainHistoryLog.newParameterInstance(domainParameter));
    }

    @Override
    @Transactional
    public void updateAutorisation(Long id, DomainCollaborator domainCollaborator, DomainRequest domainRequest, Date periodStart, Date periodEnd, boolean equipement, String motive,String status, DomainProject domainProject) throws DomainException {
        DomainAutorisation domainAutorisation = DomainAutorisation.updateInstance(domainCollaborator, domainRequest, periodStart, periodEnd, domainProject, equipement, motive, status, id);
        Authorisation authorisation = authorisationRepository.findOne(id);
        if (authorisation == null) {
            throw new DomainException("This authorisation does not exist");
      
        }
        authorisation.setId(domainAutorisation.getId());
        Collaborator collaborator = collaboratorMapper.toOneEntity(domainAutorisation.getDomainCollaborator());
        collaboratorServices.updateCollaborator(
        		domainAutorisation.getDomainCollaborator().getId(),
        		domainAutorisation.getDomainCollaborator().getLoginOpen(),
        		domainAutorisation.getDomainCollaborator().getFirstName(),
        		domainAutorisation.getDomainCollaborator().getLastName(),
        		domainAutorisation.getDomainCollaborator().getEmailOpen(),
        		domainAutorisation.getDomainCollaborator().getBuOpen()
        		);
        authorisation.setCollaborator(collaborator);
        
        Project project = projectMapper.toOneEntity(domainAutorisation.getDomainProject());
        projectServices.updateProject(
        		domainAutorisation.getDomainProject().getId(), 
        		domainAutorisation.getDomainProject().getProjectName(), 
        		domainAutorisation.getDomainProject().getPeriodStart(), 
        		domainAutorisation.getDomainProject().getPeriodEnd()
        		);
        authorisation.setProject(project);
        
        Request request = requestMapper.toOneEntity(domainAutorisation.getDomainRequest());
        requestServices.updateRequest(
        		domainAutorisation.getDomainRequest().getId(), 
        		domainAutorisation.getDomainRequest().getApplicant(), 
        		domainAutorisation.getDomainRequest().getDecider(), 
        		domainAutorisation.getDomainRequest().getRequestDate(),
        		domainAutorisation.getDomainRequest().getReplyDate(),
        		domainAutorisation.getDomainRequest().getApplicantEmail()
        		);
        authorisation.setRequest(request);
        authorisation.setMotive(domainAutorisation.getMotive());
        authorisation.setEquipment(domainAutorisation.isEquipement());
        authorisation.setPeriodStart(domainAutorisation.getPeriodStart());
        authorisation.setPeriodEnd(domainAutorisation.getPeriodEnd());
        authorisation.setStatus(domainAutorisation.getStatus());
        
        //authorisationRepository.save(authorisation);   // si on l'active , il fait un delete donc les set suffisent le 08 fevrier 2017
    }

    @Override
    @Transactional
    public void deleteAutorisation(Long id, DomainCollaborator domainCollaborator, DomainRequest domainRequest, Date periodStart, Date periodEnd, boolean equipement, String motive, String status,DomainProject domainProject) throws DomainException {
        DomainAutorisation domainAutorisation = DomainAutorisation.deleteInstance(domainCollaborator, domainRequest, periodStart, periodEnd, domainProject, equipement, motive, status, id);
        Authorisation authorisation = new Authorisation(collaboratorMapper.toOneEntity(domainAutorisation.getDomainCollaborator()),
                requestMapper.toOneEntity(domainAutorisation.getDomainRequest()),
                domainAutorisation.getPeriodStart(),
                domainAutorisation.getPeriodEnd(),
                domainAutorisation.isEquipement(),
                domainAutorisation.getMotive(),
                projectMapper.toOneEntity(domainAutorisation.getDomainProject())
                );
        authorisation.setStatus(domainAutorisation.getStatus());
        authorisation.setId(domainAutorisation.getId());

        Authorisation existingAuthorisation = authorisationRepository.findOne(authorisation.getId());
        if (existingAuthorisation == null) {
            throw new DomainException("This Authorisation has not be found");
        } else {
            //logTrackParameter(now, "DEFAULT DELETE MESSAGE", DomainHistoryLog.newParameterInstance(domainParameter));
        	
            authorisationRepository.delete(existingAuthorisation);
        }
    }

    @Override
    @Transactional
    public DomainAutorisation findAutorisation(Long id) throws DomainException {  
		Authorisation authorisation = new Authorisation();
		authorisation = authorisationRepository.findOne(id);

		DomainAutorisation domainAutorisation = new DomainAutorisation(collaboratorMapper.toOneDomain(authorisation.getCollaborator()), 
				requestMapper.toOneDomain(authorisation.getRequest()),
				authorisation.getPeriodStart(), 
				authorisation.getPeriodEnd(), 
				projectMapper.toOneDomain(authorisation.getProject()), 
				authorisation.isEquipment(),
				authorisation.getMotive(),
				authorisation.getStatus(),
				authorisation.getId()
				);
		domainAutorisation = autorisationMapper.toOneDomain(authorisation);
		return domainAutorisation;
	}

    @Override
    @Transactional
    public List<DomainAutorisation> authorisationHistory() throws DomainException {
        List<Authorisation> authorisations = authorisationRepository.findAll();
        return autorisationMapper.convertEntityListToDomainList(authorisations);
    }

    @Override
    @Transactional
    public List<DomainAutorisation> authorisationHistory(String applicantLogin) throws DomainException {
        List<Authorisation> authorisations = authorisationRepository.findByApplicant(applicantLogin);
        List<DomainAutorisation> domainList = autorisationMapper.convertEntityListToDomainList(authorisations);
        return domainList;
    }
    
    @Override
    @Transactional
    public void updateStatus(Long id, String decision) throws DomainException{
    	
        Authorisation authorisation = authorisationRepository.findOne(id);
        Request request = authorisation.getRequest();
        //request.setApplicant("user");
        request.setReplyDate(new Date()); //today
        authorisation.setStatus(decision);
        
        requestRepository.save(request);
        authorisationRepository.save(authorisation);
        
    }

    @Override
    @Transactional
    public List<DomainAutorisation> activeAuthorisations(Date date) throws DomainException {
        List<DomainAutorisation> result = new ArrayList<DomainAutorisation>();
        List<Authorisation> query = authorisationRepository.activeAuthorisations(date);

        for (Authorisation authorisation : query) {
            result.add(autorisationMapper.toOneDomain(authorisation));
        }
        return result;
    }
    
    @Override
    @Transactional
    public List<DomainAutorisation> findAuthorisationByProject(String projectName) throws DomainException
    {
    	List<Project> projectSearchList = projectRepository.findByNameProject(projectName);
    	Project projectSearch = new Project();
    	if(projectSearchList.size() > 0)
    	{
    		for(Project p : projectSearchList)
    		{
    			projectSearch = p;
    		}
    		
    		List<Authorisation> autorisationList = authorisationRepository.searchByProject(projectSearch);
    		List<DomainAutorisation> domainAutorisationList = autorisationMapper.convertEntityListToDomainList(autorisationList);
    		return domainAutorisationList;
    	}
    	else {
    		return null;
    	}
    	
    }
    
    @Override
    @Transactional
    public List<DomainAutorisation> findAuthorisationByCollaborator(String loginOpen, String emailOpen) throws DomainException
    {
    	List<Collaborator> collaboratorSearchList = collaboratorRepository.findByLoginOpenOrEmailOpen(loginOpen, emailOpen);
    	Collaborator collaboratorSearch = new Collaborator();
    	if(collaboratorSearchList.size() > 0)
    	{
    		for(Collaborator c : collaboratorSearchList)
    		{
    			collaboratorSearch = c;
    		}
    		List<Authorisation> autorisationList = authorisationRepository.getByCollaborator(collaboratorSearch);
    		List<DomainAutorisation> domainAutorisationList = autorisationMapper.convertEntityListToDomainList(autorisationList);
    		return domainAutorisationList;
    	}
    	else {
    		return null;
    	}
    	
    }
    
    /**
     * Track an history log - Can be provide as an internal transactional service
     * @param now
     * @param message
     * @param domainHistoryLog
     */
    private void logTrackParameter(Date now, String message, DomainHistoryLog domainHistoryLog) {
        HistoryLog historyLog = new HistoryLog(domainHistoryLog.getDynRootContextName(),
                domainHistoryLog.getSourceId(),
                now,
                domainHistoryLog.getUserId(),
                domainHistoryLog.getNewState(),
                domainHistoryLog.getMessage());

        historyLog.setMessage(message);
        historyLogRepository.save(historyLog);
    }

    @Override
    @Transactional
    public Set<DomainAutorisation> listAll() throws DomainException {
        return new HashSet<>(autorisationMapper.toDomains(authorisationRepository.findAll()));
    }

    @Override
    public <T extends DomainAutorisation> void createAutorisation(T typedAutorisation) throws DomainException {
        createAutorisation(typedAutorisation.getDomainCollaborator(),
                            typedAutorisation.getDomainRequest(),
                             typedAutorisation.getPeriodStart(),
                             typedAutorisation.getPeriodEnd(),
                             typedAutorisation.isEquipement(),
                             typedAutorisation.getStatus(),
                             typedAutorisation.getMotive(),
                             typedAutorisation.getDomainProject()

                );
    }

    @Override
    public <T extends DomainAutorisation> void updateAutorisation(T typedAutorisation) throws DomainException {
        updateAutorisation( typedAutorisation.getId(),
                typedAutorisation.getDomainCollaborator(),
                typedAutorisation.getDomainRequest(),
                typedAutorisation.getPeriodStart(),
                typedAutorisation.getPeriodEnd(),
                typedAutorisation.isEquipement(),
                typedAutorisation.getStatus(),
                typedAutorisation.getMotive(),
                typedAutorisation.getDomainProject()

        );
    }

    @Override
    public <T extends DomainAutorisation> void deleteAutorisation(T typedAutorisation) throws DomainException {
        deleteAutorisation( typedAutorisation.getId(),
                typedAutorisation.getDomainCollaborator(),
                typedAutorisation.getDomainRequest(),
                typedAutorisation.getPeriodStart(),
                typedAutorisation.getPeriodEnd(),
                typedAutorisation.isEquipement(),
                typedAutorisation.getStatus(),
                typedAutorisation.getMotive(),
                typedAutorisation.getDomainProject()
        );
        
       // deleteAutorisation(Long id, DomainCollaborator domainCollaborator, DomainRequest domainRequest, Date periodStart, Date periodEnd, boolean equipement, String motive, String status,DomainProject domainProject)
    }
}