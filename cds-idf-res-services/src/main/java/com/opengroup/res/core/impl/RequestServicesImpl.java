package com.opengroup.res.core.impl;

import com.opengroup.res.core.RequestServices;
import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.core.domain.DomainHistoryLog;
import com.opengroup.res.core.domain.DomainRequest;
import com.opengroup.res.core.impl.mappers.RequestMapper;
import com.opengroup.res.jpa.HistoryLogRepository;
import com.opengroup.res.jpa.RequestRepository;
//import com.opengroup.res.jpa.entities.AuditState;
import com.opengroup.res.jpa.entities.HistoryLog;
import com.opengroup.res.jpa.entities.Request;
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
public class RequestServicesImpl implements RequestServices {

    @Autowired
    private HistoryLogRepository historyLogRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private RequestMapper requestMapper;

    @Override
    @Transactional
    public void createRequest(String applicant, String decider, Date requestDate, Date replyDate) throws DomainException {
       Date reqDate = new Date();
        String dcd = "";
        Date repDate = null;
        Long id = null;
        DomainRequest domainRequest = DomainRequest.newInstance(applicant,dcd ,reqDate , repDate, id);
        requestRepository.save(requestMapper.toOneEntity(domainRequest));
        //logTrackParameter(now,"DEFAULT CREATION MESSAGE", DomainHistoryLog.newParameterInstance(domainParameter));
    }

    @Override
    @Transactional
    public void updateRequest(Long id, String applicant, String decider, Date requestDate, Date replyDate) throws DomainException {
        DomainRequest domainRequest = DomainRequest.updateInstance(applicant,decider ,requestDate , replyDate, id);
        Request request = requestRepository.findOne(domainRequest.getId());
        if (request == null) {
            throw new DomainException("This request does not exist");
        }
        request.setApplicant(domainRequest.getApplicant().toString());
        request.setDecider(domainRequest.getDecider().toString());
        request.setRequestDate(domainRequest.getRequestDate());
        request.setReplyDate(new Date());
        //logTrackParameter(now,  "DEFAULT UPDATE MESSAGE", DomainHistoryLog.newParameterInstance(domainParameter));
        requestRepository.save(request);
    }

    @Override
    @Transactional
    public void deleteRequest(Long id, String applicant, String decider, Date requestDate, Date replyDate) throws DomainException {
        DomainRequest domainRequest = DomainRequest.deleteInstance(applicant, decider, requestDate, replyDate, id);
        Request request = new Request( domainRequest.getApplicant(),
                                 domainRequest.getDecider(),
                                 domainRequest.getRequestDate(),
                                 domainRequest.getReplyDate()
                    );
        request.setId(domainRequest.getId());

        Request existingRequest = requestRepository.findOne(request.getId());
        if (existingRequest == null) {
            throw new DomainException("This Request has not be found");
        } else {
            //logTrackParameter(now, "DEFAULT DELETE MESSAGE", DomainHistoryLog.newParameterInstance(domainParameter));
            requestRepository.delete(existingRequest);
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
    public Set<DomainRequest> listAll() throws DomainException {
        return new HashSet<>(requestMapper.toDomains(requestRepository.findAll()));
    }

    @Override
    public <T extends DomainRequest> void createRequest(T typedRequest) throws DomainException {
        createRequest(typedRequest.getApplicant(),typedRequest.getDecider(), typedRequest.getRequestDate(),  typedRequest.getReplyDate());
    }

    @Override
    public <T extends DomainRequest> void updateRequest(T typedRequest) throws DomainException {
        updateRequest(typedRequest.getId(),typedRequest.getApplicant(),typedRequest.getDecider(), typedRequest.getRequestDate(), typedRequest.getReplyDate());
    }

    @Override
    public <T extends DomainRequest> void deleteRequest(T typedRequest) throws DomainException {
        deleteRequest(typedRequest.getId(),typedRequest.getApplicant(),typedRequest.getDecider(),typedRequest.getRequestDate(), typedRequest.getReplyDate() );
    }
}