package com.opengroup.res.core.impl;

import com.opengroup.res.core.ProjectServices;
import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.core.domain.DomainHistoryLog;
import com.opengroup.res.core.domain.DomainProject;
//import com.opengroup.res.core.impl.mappers.ParameterMapper;
import com.opengroup.res.core.impl.mappers.ProjectMapper;
import com.opengroup.res.jpa.HistoryLogRepository;
import com.opengroup.res.jpa.ProjectRepository;
//import com.opengroup.res.jpa.entities.AuditState;
import com.opengroup.res.jpa.entities.HistoryLog;
import com.opengroup.res.jpa.entities.Project;
//import com.opengroup.res.jpa.entities.ParameterPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementation to manage Project service
 *
 * @author Open groupe
 * @since 1.0.0
 */
@Service
public class ProjectServicesImpl implements ProjectServices {

    @Autowired
    private HistoryLogRepository historyLogRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    @Transactional
    public void createProject(String projectName, Date periodStart, Date periodEnd) throws DomainException {
        Long id = 1L;
        DomainProject domainProject = DomainProject.newInstance(projectName, periodStart, periodEnd,id);
        projectRepository.save(projectMapper.toOneEntity(domainProject));
        //logTrackParameter(now,"DEFAULT CREATION MESSAGE", DomainHistoryLog.newParameterInstance(domainParameter));
    }

    @Override
    @Transactional
    public void updateProject(Long id,String projectName, Date periodStart, Date periodEnd) throws DomainException {
        DomainProject domainProject = DomainProject.updateInstance(projectName, periodStart, periodEnd,id);
        Project project = projectRepository.findOne(domainProject.getId());
        if (project == null) {
            throw new DomainException("This project does not exist");
        }
        project.setIdProject(domainProject.getId());
        project.setNameProject(domainProject.getProjectName());
        project.setPeriodStart(domainProject.getPeriodStart());
        project.setPeriodEnd(domainProject.getPeriodEnd());

        //logTrackParameter(now,  "DEFAULT UPDATE MESSAGE", DomainHistoryLog.newParameterInstance(domainParameter));
        projectRepository.save(project);
    }

    @Override
    @Transactional
    public void deleteProject(Long id,String projectName, Date periodStart, Date periodEnd) throws DomainException {
        DomainProject domainProject = DomainProject.deleteInstance(projectName, periodStart, periodEnd,id);
        Project project = new Project( domainProject.getId(), domainProject.getProjectName(), domainProject.getPeriodStart(), domainProject.getPeriodEnd() );

        Project existingProject = projectRepository.findOne(project.getIdProject());
        if (existingProject == null) {
            throw new DomainException("This project has not be found");
        } else {
            //logTrackParameter(now, "DEFAULT DELETE MESSAGE", DomainHistoryLog.newParameterInstance(domainParameter));
            projectRepository.delete(existingProject);
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
    public Set<DomainProject> listAll() throws DomainException {
        return new HashSet<>(projectMapper.toDomains(projectRepository.findAll()));
    }

    @Override
    public <T extends DomainProject> void createProject(T typedProject) throws DomainException {
        createProject(typedProject.getProjectName(),typedProject.getPeriodStart(), typedProject.getPeriodEnd());
    }

    @Override
    public <T extends DomainProject> void updateProject(T typedProject) throws DomainException {
        updateProject(typedProject.getId(),typedProject.getProjectName(),typedProject.getPeriodStart(), typedProject.getPeriodEnd());
    }

    @Override
    public <T extends DomainProject> void deleteProject(T typedProject) throws DomainException {
        deleteProject(typedProject.getId(),typedProject.getProjectName(),typedProject.getPeriodStart(), typedProject.getPeriodEnd());
    }
}