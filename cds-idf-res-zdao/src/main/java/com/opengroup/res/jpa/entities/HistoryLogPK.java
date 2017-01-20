package com.opengroup.res.jpa.entities;

import com.opengroup.res.util.EntityBean;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * The primary key class for the history_log database table.
 */
@Embeddable
public class HistoryLogPK implements Serializable, EntityBean {
    //default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;

    @Column(name = "root_context_name", nullable = false, length = 100)
    private String rootContextName;

    @Column(name = "source_id", nullable = false, length = 30)
    private String sourceId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date", nullable = false)
    private java.util.Date creationDate;

    /**
     * Default constructor
     */
    public HistoryLogPK() {
        //Default
    }

    /**
     *
     * @param rootContextName
     * @param sourceId
     * @param creationDate
     */
    public HistoryLogPK(String rootContextName, String sourceId, Date creationDate) {
        this.rootContextName = rootContextName;
        this.sourceId = sourceId;
        this.creationDate = creationDate;
    }

    /**
     * Gets sourceId.
     *
     * @return Value of sourceId.
     */
    public String getSourceId() {
        return sourceId;
    }

    /**
     * Sets new rootContextName.
     *
     * @param rootContextName New value of rootContextName.
     */
    public void setRootContextName(String rootContextName) {
        this.rootContextName = rootContextName;
    }

    /**
     * Sets new sourceId.
     *
     * @param sourceId New value of sourceId.
     */
    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
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
     * Gets rootContextName.
     *
     * @return Value of rootContextName.
     */
    public String getRootContextName() {
        return rootContextName;
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
     *
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HistoryLogPK)) {
            return false;
        }
        HistoryLogPK castOther = (HistoryLogPK) other;
        return
                this.rootContextName.equals(castOther.rootContextName)
                        && this.sourceId.equals(castOther.sourceId)
                        && this.creationDate.equals(castOther.creationDate);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.rootContextName.hashCode();
        hash = hash * prime + this.sourceId.hashCode();
        hash = hash * prime + this.creationDate.hashCode();

        return hash;
    }
}