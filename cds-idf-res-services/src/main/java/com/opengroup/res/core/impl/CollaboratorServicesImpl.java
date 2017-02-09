package com.opengroup.res.core.impl;

import com.opengroup.res.core.CollaboratorServices;
import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.core.domain.DomainHistoryLog;
import com.opengroup.res.core.domain.DomainCollaborator;
import com.opengroup.res.core.impl.mappers.CollaboratorMapper;
import com.opengroup.res.jpa.HistoryLogRepository;
import com.opengroup.res.jpa.CollaboratorRepository;
//import com.opengroup.res.jpa.entities.AuditState;
import com.opengroup.res.jpa.entities.HistoryLog;
import com.opengroup.res.jpa.entities.Collaborator;
//import com.opengroup.res.jpa.entities.ParameterPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementation to manage Parameter service
 *
 * @author Open groupe
 * @since 1.0.0
 */
@Service
public class CollaboratorServicesImpl implements CollaboratorServices {

    @Autowired
    private HistoryLogRepository historyLogRepository;

    @Autowired
    private CollaboratorRepository collaboratorRepository;

    @Autowired
    private CollaboratorMapper collaboratorMapper;

    @Override
    @Transactional
    public void createCollaborator(String loginOpen, String firstName, String lastName, String emailOpen, String bu) throws DomainException {
        Long id = null;
    	DomainCollaborator domainCollaborator = DomainCollaborator.newInstance(loginOpen, firstName, lastName, emailOpen, bu,id);
        collaboratorRepository.save(collaboratorMapper.toOneEntity(domainCollaborator));
        //logTrackParameter(now,"DEFAULT CREATION MESSAGE", DomainHistoryLog.newParameterInstance(domainParameter));
    }

    @Override
    @Transactional
    public void updateCollaborator(Long id, String loginOpen, String firstName, String lastName, String emailOpen, String bu) throws DomainException {
        DomainCollaborator domainCollaborator = DomainCollaborator.updateInstance(loginOpen, firstName, lastName, emailOpen, bu, id);
        Collaborator collaborator = collaboratorRepository.findOne(id);
        if (collaborator == null) {
            throw new DomainException("This collaborator does not exist");

        }
        collaborator.setId(id);
       // collaborator.setId(domainCollaborator.getId());
        collaborator.setLoginOpen(domainCollaborator.getLoginOpen());
       // collaborator.setLoginOpen(loginOpen);
        collaborator.setFirstName(domainCollaborator.getFirstName());
        //collaborator.setFirstName(firstName);
        collaborator.setLastName(domainCollaborator.getLastName());
        // collaborator.setLastName(lastName);
        //collaborator.setEmailOpen(emailOpen);
        collaborator.setEmailOpen(domainCollaborator.getEmailOpen());
        //collaborator.setBu(bu);
        collaborator.setBu(domainCollaborator.getBuOpen());
        //logTrackParameter(now,  "DEFAULT UPDATE MESSAGE", DomainHistoryLog.newParameterInstance(domainParameter));
        collaboratorRepository.save(collaborator);
    }

    @Override
    @Transactional
    public void deleteCollaborator(Long id, String loginOpen, String firstName, String lastName, String emailOpen, String bu) throws DomainException {
        DomainCollaborator domainCollaborator = DomainCollaborator.deleteInstance(loginOpen, firstName, lastName, emailOpen, bu, id);
        Collaborator collaborator = new Collaborator(domainCollaborator.getLoginOpen(),
                                            domainCollaborator.getFirstName(),
                                            domainCollaborator.getLastName(),
                                            domainCollaborator.getEmailOpen(),
                                            domainCollaborator.getBuOpen()
                                            );
        collaborator.setId(domainCollaborator.getId());

        Collaborator existingCollaborator = collaboratorRepository.findOne(collaborator.getId());
        if (existingCollaborator == null) {
            throw new DomainException("This collaborator has not be found");
        } else {
            //logTrackParameter(now, "DEFAULT DELETE MESSAGE", DomainHistoryLog.newParameterInstance(domainParameter));
            collaboratorRepository.delete(existingCollaborator);
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
    public Set<DomainCollaborator> listAll() throws DomainException {
        return new HashSet<>(collaboratorMapper.toDomains(collaboratorRepository.findAll()));
    }

    @Override
    public <T extends DomainCollaborator> void createCollaborator(T typedCollaborator) throws DomainException {
        createCollaborator(typedCollaborator.getLoginOpen(),typedCollaborator.getFirstName(),typedCollaborator.getLastName(),typedCollaborator.getEmailOpen(),typedCollaborator.getBuOpen());
    }

    @Override
    public <T extends DomainCollaborator> void updateCollaborator(T typedCollaborator) throws DomainException {
        updateCollaborator(typedCollaborator.getId(),typedCollaborator.getLoginOpen(),typedCollaborator.getFirstName(),typedCollaborator.getLastName(),typedCollaborator.getEmailOpen(),typedCollaborator.getBuOpen());
    }

    @Override
    public <T extends DomainCollaborator> void deleteCollaborator(T typedCollaborator) throws DomainException {
        deleteCollaborator(typedCollaborator.getId(),typedCollaborator.getLoginOpen(),typedCollaborator.getFirstName(),typedCollaborator.getLastName(),typedCollaborator.getEmailOpen(),typedCollaborator.getBuOpen());
    }
}