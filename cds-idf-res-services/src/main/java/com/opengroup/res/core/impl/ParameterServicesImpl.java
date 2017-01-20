package com.opengroup.res.core.impl;

import com.opengroup.res.core.ParameterServices;
import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.core.domain.DomainHistoryLog;
import com.opengroup.res.core.domain.DomainParameter;
import com.opengroup.res.core.impl.mappers.ParameterMapper;
import com.opengroup.res.jpa.HistoryLogRepository;
import com.opengroup.res.jpa.ParameterRepository;
import com.opengroup.res.jpa.entities.AuditState;
import com.opengroup.res.jpa.entities.HistoryLog;
import com.opengroup.res.jpa.entities.Parameter;
import com.opengroup.res.jpa.entities.ParameterPK;
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
public class ParameterServicesImpl implements ParameterServices {

    @Autowired
    private HistoryLogRepository historyLogRepository;

    @Autowired
    private ParameterRepository parameterRepository;

    @Autowired
    private ParameterMapper parameterMapper;

    @Override
    @Transactional
    public void createParameter(DomainParameter.ParameterContext context, String code, String value, String userId) throws DomainException {
        Date now = new Date();
        DomainParameter domainParameter = DomainParameter.newCreatedStateInstance(context, code, value, userId);
        parameterRepository.save(parameterMapper.toOneEntity(domainParameter));
        logTrackParameter(now,"DEFAULT CREATION MESSAGE", DomainHistoryLog.newParameterInstance(domainParameter));
    }

    @Override
    @Transactional
    public void updateParameter(DomainParameter.ParameterContext context, String code, String value, String userId) throws DomainException {
        Date now = new Date();
        DomainParameter domainParameter = DomainParameter.newModifiedStateInstance(context, code, value, userId);
        Parameter parameter = parameterRepository.findOne(new ParameterPK(domainParameter.getContext().toString(), domainParameter.getKey()));
        if (parameter == null) {
            throw new DomainException("This parameter does not exist");
        }
        parameter.setParamValue(domainParameter.getValue());
        parameter.setParameterState(domainParameter.getState().toString());
        parameter.setAuditState(new AuditState(parameter.getAuditState().getCreationDate(),now,domainParameter.getLastUpdateUserId()));
        logTrackParameter(now,  "DEFAULT UPDATE MESSAGE", DomainHistoryLog.newParameterInstance(domainParameter));
        parameterRepository.save(parameter);
    }

    @Override
    @Transactional
    public void deleteParameter(DomainParameter.ParameterContext context, String code, String userId) throws DomainException {
        Date now = new Date();
        DomainParameter domainParameter = DomainParameter.newSuppressedStateInstance(context, code, userId);
        Parameter parameter = new Parameter(domainParameter.getContext().toString(),
                                            domainParameter.getKey(),
                                            domainParameter.getValue());

        Parameter existingParameter = parameterRepository.findOne(parameter.getId());
        if (existingParameter == null) {
            throw new DomainException("This parameter has not be found");
        } else {
            logTrackParameter(now, "DEFAULT DELETE MESSAGE", DomainHistoryLog.newParameterInstance(domainParameter));
            parameterRepository.delete(existingParameter);
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
    public Set<DomainParameter> listAll() throws DomainException {
        return new HashSet<>(parameterMapper.toDomains(parameterRepository.findAll()));
    }

    @Override
    public <T extends DomainParameter> void createParameter(T typedParameter) throws DomainException {
        createParameter(typedParameter.getContext(),typedParameter.getKey(), typedParameter.getValue(), typedParameter.getLastUpdateUserId());
    }

    @Override
    public <T extends DomainParameter> void updateParameter(T typedParameter) throws DomainException {
        updateParameter(typedParameter.getContext(),typedParameter.getKey(), typedParameter.getValue(), typedParameter.getLastUpdateUserId());
    }

    @Override
    public <T extends DomainParameter> void deleteParameter(T typedParameter) throws DomainException {
        deleteParameter(typedParameter.getContext(),typedParameter.getKey(), typedParameter.getLastUpdateUserId());
    }
}