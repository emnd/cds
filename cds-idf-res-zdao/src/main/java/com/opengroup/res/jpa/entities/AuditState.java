package com.opengroup.res.jpa.entities;

import com.opengroup.res.util.EntityBean;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Common persistent properties tables can have
 *
 * @author Open group
 * @since 1.0.0
 */
@Embeddable
public class AuditState implements EntityBean {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    private Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_modification_date", nullable = false)
    private Date lastModificationDate;

    @Column(name = "last_update_user", nullable = false, length = 30)
    private String lastUpdateUser;

    /**
     * Default constructor
     */
    public AuditState() {
        //Default constructor
    }

    /**
     *
     * @param creationDate
     * @param lastModificationDate
     * @param lastUpdateUser
     */
    public AuditState(Date creationDate, Date lastModificationDate, String lastUpdateUser) {
        this.creationDate = creationDate;
        this.lastModificationDate = lastModificationDate;
        this.lastUpdateUser = lastUpdateUser;
    }

    /**
     * Sets new creationDate.
     *
     * @param creationDate New value of creationDate.
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Gets creationDate.
     *
     * @return Value of creationDate.
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Sets new lastModificationDate.
     *
     * @param lastModificationDate New value of lastModificationDate.
     */
    public void setLastModificationDate(Date lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    /**
     * Gets lastUpdateUser.
     *
     * @return Value of lastUpdateUser.
     */
    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    /**
     * Gets lastModificationDate.
     *
     * @return Value of lastModificationDate.
     */
    public Date getLastModificationDate() {
        return lastModificationDate;
    }

    /**
     * Sets new lastUpdateUser.
     *
     * @param lastUpdateUser New value of lastUpdateUser.
     */
    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }
}
