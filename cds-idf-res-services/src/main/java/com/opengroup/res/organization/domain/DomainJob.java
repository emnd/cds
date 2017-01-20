package com.opengroup.res.organization.domain;

import com.opengroup.res.core.domain.DomainBean;
import com.opengroup.res.core.domain.DomainException;
import org.springframework.util.StringUtils;

/**
 * A Job class definition
 *
 * @author Open group
 * @since 1.0.0
 */
public final class DomainJob extends DomainBean {

    protected String function;

    protected String department;

    /**
     * @param function
     * @param department
     * @throws DomainException
     */
    private DomainJob(String function, String department) throws DomainException {
        if (StringUtils.isEmpty(function)) {
            constraintsErrors.add("A job cant't be created without a function");
        }

        if (StringUtils.isEmpty(department)) {
            constraintsErrors.add("A job cant't be created without a department");
        }

        checkIfValid();
        this.function = function;
        this.department = department;
    }

    /**
     * Build a new job instance
     *
     * @param function
     * @param department
     * @return
     * @throws DomainException
     */
    public static DomainJob newInstance(String function, String department) throws DomainException {
        return new DomainJob(function, department);
    }
}
