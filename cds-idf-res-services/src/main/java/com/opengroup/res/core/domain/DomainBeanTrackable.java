package com.opengroup.res.core.domain;

import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * Provide abstraction upon audit action such as creation date, last update date and last update user
 */
public abstract class DomainBeanTrackable extends DomainBean implements Serializable {

    private Date creationDate;

    private Date lastModificationdate;

    private String lastUpdateUserId;

    /**
     * @param lastUpdateUserId
     */
    protected DomainBeanTrackable(String lastUpdateUserId) {
        if (StringUtils.isEmpty(lastUpdateUserId)) {
            constraintsErrors.add("User must be defined");
        }
        this.lastUpdateUserId = lastUpdateUserId;
    }

    public DomainBeanTrackable() {
		// TODO Auto-generated constructor stub
	}

	/**
     * @param creationDate
     * @param lastModificationDate
     * @throws DomainException
     */
    public void assignDate(Date creationDate, Date lastModificationDate) throws DomainException {
        if (creationDate == null) {
            constraintsErrors.add("A creation date must be provided");
        }
        if (lastModificationDate == null) {
            constraintsErrors.add("A last modification date must be provided");
        }

        if (creationDate != null && lastModificationDate != null && creationDate.after(lastModificationDate)) {
            constraintsErrors.add("creation date must be before or equal to last modification date");
        }
        checkIfValid();

    }

    /**
     * Gets lastModificationdate.
     *
     * @return Value of lastModificationdate.
     */
    public Date getLastModificationdate() {
        return lastModificationdate;
    }

    /**
     * Gets lastUpdateUserId.
     *
     * @return Value of lastUpdateUserId.
     */
    public String getLastUpdateUserId() {
        return lastUpdateUserId;
    }

    /**
     * Gets creationDate.
     *
     * @return Value of creationDate.
     */
    public Date getCreationDate() {
        return creationDate;
    }
}
