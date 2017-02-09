package com.opengroup.res.core.impl;

import com.opengroup.res.core.AutorisationServices;
import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.core.domain.DomainHistoryLog;
import com.opengroup.res.core.domain.DomainAutorisation;
import com.opengroup.res.core.impl.mappers.AutorisationMapper;
import com.opengroup.res.core.impl.mappers.CollaboratorMapper;
import com.opengroup.res.core.impl.mappers.ProjectMapper;
import com.opengroup.res.core.impl.mappers.RequestMapper;
import com.opengroup.res.jpa.HistoryLogRepository;
import com.opengroup.res.jpa.AuthorisationRepository;
import com.opengroup.res.jpa.entities.Authorisation;
import com.opengroup.res.jpa.entities.HistoryLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
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

    @Override
    @Transactional
    public void createAutorisation(DomainCollaborator domainCollaborator, DomainRequest domainRequest, Date periodStart, Date periodEnd, boolean equipement, String motive,String status, DomainProject domainProject) throws DomainException {
    	Long id = 1L;
    	DomainAutorisation domainAutorisation = DomainAutorisation.newInstance(domainCollaborator, domainRequest, periodStart, periodEnd, domainProject, equipement, motive, status,id);
        domainAutorisation.setStatus("Attente"); // initialisation du statut en attente lors de la création
        domainAutorisation.setMotive(motive);
       // domainAutorisation.setDomainCollaborator(domainCollaborator);
        authorisationRepository.save(autorisationMapper.toOneEntity(domainAutorisation));
        //logTrackParameter(now,"DEFAULT CREATION MESSAGE", DomainHistoryLog.newParameterInstance(domainParameter));
    }

    @Override
    @Transactional/*(isolation = Isolation.READ_COMMITTED)*/
    public void updateAutorisation(Long id, DomainCollaborator domainCollaborator, DomainRequest domainRequest, Date periodStart, Date periodEnd, boolean equipement, String motive,String status, DomainProject domainProject) throws DomainException {
        DomainAutorisation domainAutorisation = DomainAutorisation.updateInstance(domainCollaborator, domainRequest, periodStart, periodEnd, domainProject, equipement, motive, status, id);
        Authorisation authorisation = authorisationRepository.findOne(id);
        if (authorisation == null) {
            throw new DomainException("This authorisation does not exist");
      
        }
        authorisation.setId(domainAutorisation.getId());
        //authorisation.setCollaborator(collaboratorMapper.toOneEntity(domainAutorisation.getDomainCollaborator()));
        //authorisation.setProject(projectMapper.toOneEntity(domainAutorisation.getDomainProject());
        //authorisation.setRequest(requestMapper.toOneEntity(domainAutorisation.getDomainRequest()));
       // authorisation.setMotive(domainAutorisation.getMotive());
        authorisation.setMotive(motive);
        authorisation.setEquipment(equipement);
       // authorisation.setPeriodStart(domainAutorisation.getPeriodStart());
       // authorisation.setPeriodEnd(domainAutorisation.getPeriodEnd());
        authorisation.setStatus(status);
        
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