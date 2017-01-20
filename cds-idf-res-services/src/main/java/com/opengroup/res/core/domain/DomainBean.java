package com.opengroup.res.core.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the base class of all domain class objects. It's purpose is to provide a common template and to be generically handled by other system
 *
 * @author Open groupe
 * @since 1.0.0
 */
public abstract class DomainBean implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(DomainBean.class);

    protected transient List<String> constraintsErrors;

    protected DomainBean() {
        constraintsErrors = new ArrayList<>();
    }

    /**
     * A tool to print errors related to domain operation
     */
    protected void checkIfValid() throws DomainException {
        if (!constraintsErrors.isEmpty()) {
            constraintsErrors.forEach(LOGGER::error);
            throw new DomainException("Error while creating bean of type " + this.getClass());
        }
    }
}
