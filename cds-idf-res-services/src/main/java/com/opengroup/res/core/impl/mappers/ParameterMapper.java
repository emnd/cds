package com.opengroup.res.core.impl.mappers;

import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.core.domain.DomainParameter;
import com.opengroup.res.jpa.entities.Parameter;
import com.opengroup.res.jpa.entities.AuditState;
import com.opengroup.res.util.AbstractDomainMapper;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * A parameter mapper
 */
@Component
public class ParameterMapper extends AbstractDomainMapper<DomainParameter, Parameter> {

    @Override
    public DomainParameter toOneDomain(Parameter entity) throws DomainException {
        String context = entity.getId().getContext();
        String code = entity.getId().getCode();
        String paramValue = entity.getParamValue();
        String lastUpdateUser = entity.getAuditState().getLastUpdateUser();

        switch(DomainParameter.ParameterStatus.valueOf(entity.getParameterState())){
            case CREATED:
                return DomainParameter.newCreatedStateInstance(DomainParameter.ParameterContext.valueOf(context), code, paramValue, lastUpdateUser);
            case MODIFIED:
                return DomainParameter.newModifiedStateInstance(DomainParameter.ParameterContext.valueOf(context), code, paramValue, lastUpdateUser);
            case VALIDATED:
                return DomainParameter.newValidatedStateInstance(DomainParameter.ParameterContext.valueOf(context), code, paramValue, lastUpdateUser);
            case SUPPRESSED:
                return DomainParameter.newSuppressedStateInstance(DomainParameter.ParameterContext.valueOf(context), code, lastUpdateUser);
            default:
                throw new DomainException("A parameter with no state has been found");
        }
    }

    @Override
    public Parameter toOneEntity(DomainParameter domain) {
        Date now = new Date();
        Parameter parameter = new Parameter(domain.getContext().toString(),
                domain.getKey(),
                domain.getValue());
        parameter.setParameterState(domain.getState().toString());
        parameter.setAuditState(new AuditState(now,now,domain.getLastUpdateUserId()));
        return parameter;
    }
}
