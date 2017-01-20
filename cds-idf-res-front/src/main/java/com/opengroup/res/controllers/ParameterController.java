package com.opengroup.res.controllers;

import com.opengroup.res.core.ParameterServices;
import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.mappers.ParameterRepresentationMapper;
import com.opengroup.res.model.ParameterRepresentation;
import com.opengroup.res.util.FrontException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

/**
 * A REST Controller which provides API to manage application parameters
 *
 * @author Open group
 * @since 1.0.0
 */
@RestController
public class ParameterController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParameterController.class);

    @Autowired
    private ParameterServices parameterServices;

    @Autowired
    private ParameterRepresentationMapper parameterRepresentationMapper;

    /**
     *
     * @return
     * @throws DomainException
     */
    @RequestMapping(value = "/parameters", method = RequestMethod.GET)
    public Set<ParameterRepresentation> list() throws FrontException {
        Set<ParameterRepresentation> parameters;
        try {
            parameters = new HashSet<>(parameterRepresentationMapper.toRepresentations(parameterServices.listAll()));
        } catch (DomainException e) {
            String message = "Internal error : "+ e.getMessage();
            LOGGER.error(message, e);
            throw new FrontException(message, e);
        }
        return parameters;
    }
}
