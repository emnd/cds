package com.opengroup.res.organization.domain;

import com.opengroup.res.core.domain.DomainBean;
import com.opengroup.res.core.domain.DomainException;

/**
 * An employee comes from somewhere in any organization and doesn't have rights to manipulate
 * the application until it becomes a User with granted roles.
 *
 * @author Open group
 * @since 1.0.0
 */
public class DomainEmployee extends DomainBean {

    private DomainIdentity identity;

    private DomainJob job;

    private DomainCoordinate coordinate;

    /**
     * @param identity
     * @param job
     * @param coordinate
     */
    protected DomainEmployee(DomainIdentity identity, DomainJob job, DomainCoordinate coordinate) throws DomainException {
        if (identity == null) {
            constraintsErrors.add("Employee can't be create without an identity");
        }

        if (job == null) {
            constraintsErrors.add("Employee can't be create without a job definition");
        }

        if (coordinate == null) {
            constraintsErrors.add("Employee can't be create without any coordinates");
        }

        checkIfValid();
        this.identity = identity;
        this.job = job;
        this.coordinate = coordinate;
    }

    /**
     * Build a valid complete Employee object with non null attributes
     *
     * @param identity
     * @param job
     * @param coordinate
     * @return
     * @throws DomainException
     */
    public static DomainEmployee newInstance(DomainIdentity identity, DomainJob job, DomainCoordinate coordinate) throws DomainException {
        return new DomainEmployee(identity, job, coordinate);
    }

    /**
     * Gets coordinate.
     *
     * @return Value of coordinate.
     */
    public DomainCoordinate getCoordinate() {
        return coordinate;
    }

    /**
     * Sets new coordinate.
     *
     * @param coordinate New value of coordinate.
     */
    public void setCoordinate(DomainCoordinate coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * Gets identity.
     *
     * @return Value of identity.
     */
    public DomainIdentity getIdentity() {
        return identity;
    }

    /**
     * Sets new identity.
     *
     * @param identity New value of identity.
     */
    public void setIdentity(DomainIdentity identity) {
        this.identity = identity;
    }

    /**
     * Gets job.
     *
     * @return Value of job.
     */
    public DomainJob getJob() {
        return job;
    }

    /**
     * Sets new job.
     *
     * @param job New value of job.
     */
    public void setJob(DomainJob job) {
        this.job = job;
    }
}
