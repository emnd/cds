package com.opengroup.res.jpa.entities;

import com.opengroup.res.util.EntityBean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the history_log database table.
 */
@Entity
@Table(name = "history_log")
@NamedQuery(name = "HistoryLog.findAll", query = "SELECT h FROM HistoryLog h")
public class HistoryLog implements Serializable, EntityBean {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private HistoryLogPK id;

    @Column(name = "new_state", length = 100)
    private String newState;

    @Column(name = "message", length = 250)
    private String message;

    @Column(name = "user_id", nullable = false, length = 30)
    private String userId;

    /**
     *
     */
    public HistoryLog() {
        //Default
    }

    /**
     *
     * @param rootContextName
     * @param sourceIdentifier
     * @param creationDate
     * @param userId
     * @param newState
     * @param message
     */
    public HistoryLog(String rootContextName, String sourceIdentifier, Date creationDate, String userId, String newState, String message) {
        this.id = new HistoryLogPK(rootContextName, sourceIdentifier, creationDate);
        this.userId = userId;
        this.newState = newState;
        this.message = message;
    }

    /**
     * Gets id.
     *
     * @return Value of id.
     */
    public HistoryLogPK getId() {
        return id;
    }

    /**
     * Sets new id.
     *
     * @param id New value of id.
     */
    public void setId(HistoryLogPK id) {
        this.id = id;
    }

    /**
     * Gets message.
     *
     * @return Value of message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets new message.
     *
     * @param message New value of message.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets userId.
     *
     * @return Value of userId.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets new userId.
     *
     * @param userId New value of userId.
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Gets newState.
     *
     * @return Value of newState.
     */
    public String getNewState() {
        return newState;
    }

    /**
     * Sets new newState.
     *
     * @param newState New value of newState.
     */
    public void setNewState(String newState) {
        this.newState = newState;
    }
}