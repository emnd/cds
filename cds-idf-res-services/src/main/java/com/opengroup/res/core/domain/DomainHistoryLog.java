package com.opengroup.res.core.domain;

import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * This is the History Log class object
 */
public class DomainHistoryLog extends DomainBean {

    private RootContextName rootContextName;
    private String dynRootContextName;
    private String sourceId;
    private Date creationDate;
    private String userId;
    private String message;
    private String newState;

    /**
     * A private default constructor with custom logic root context commonName
     *
     * @param rootContextName
     * @param sourceId
     * @param userId
     * @param newState
     * @throws DomainException
     */
    private DomainHistoryLog(String rootContextName, String sourceId, String userId, String newState) throws DomainException {
        this.dynRootContextName = rootContextName;
        this.sourceId = sourceId;
        this.userId = userId;
        this.newState = newState;
    }

    /**
     * Create an history log entry for a parameter object
     *
     * @param domainParameter
     * @return
     * @throws DomainException
     */
    public static DomainHistoryLog newParameterInstance(DomainParameter domainParameter) throws DomainException {
        if (domainParameter == null) {
            throw new DomainException("Domain parameter must not be null");
        }
        /*
         * The rules about format the information to trace
         */
        String rootContextName = StringUtils.capitalize(DomainHistoryLog.RootContextName.PARAMETER.toString() + "-" + domainParameter.getContext());
        return new DomainHistoryLog(rootContextName, domainParameter.getKey(), domainParameter.getLastUpdateUserId(), domainParameter.getState().toString());
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
     * Sets new creationDate.
     *
     * @param creationDate New value of creationDate.
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Gets rootContextName.
     *
     * @return Value of rootContextName.
     */
    public RootContextName getRootContextName() {
        return rootContextName;
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
     * Gets userId.
     *
     * @return Value of userId.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Gets dynRootContextName.
     *
     * @return Value of dynRootContextName.
     */
    public String getDynRootContextName() {
        return dynRootContextName;
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
     * Gets newState.
     *
     * @return Value of newState.
     */
    public String getNewState() {
        return newState;
    }

    /**
     * The inner root context enumeration
     */
    public enum RootContextName {
        PARAMETER, REGIME, MEASURE, ASSISTANCE
    }
}
