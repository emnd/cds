package com.opengroup.res.mappers;

import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.core.domain.DomainParameter;
import com.opengroup.res.util.FrontException;
import com.opengroup.res.util.mappers.AbstractFrontMapper;
import com.opengroup.res.model.ParameterRepresentation;
import org.springframework.stereotype.Component;

/**
 * Parameter representation mapper
 *
 * @author Open group
 * @since 1.0.0
 */
@Component
public class ParameterRepresentationMapper extends AbstractFrontMapper<DomainParameter, ParameterRepresentation> {

    @Override
    public DomainParameter toOneDomain(ParameterRepresentation representation) throws DomainException {
        return null;
    }

    @Override
    public ParameterRepresentation toOneRepresentation(DomainParameter domain){
        ParameterRepresentation parameterRepresentation = new ParameterRepresentation();
        parameterRepresentation.setContext(domain.getContext().toString());
        parameterRepresentation.setKey(domain.getKey());
        parameterRepresentation.setState(domain.getState().toString());
        parameterRepresentation.setValue(domain.getValue());
        return parameterRepresentation;
    }
}
