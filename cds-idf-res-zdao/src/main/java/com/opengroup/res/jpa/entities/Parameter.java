package com.opengroup.res.jpa.entities;

import com.opengroup.res.util.EntityBean;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the parameter database table.
 */
@Entity
@Table(name = "parameter")
@NamedQuery(name = "Parameter.findAll", query = "SELECT p FROM Parameter p")
public class Parameter implements Serializable, EntityBean {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ParameterPK id;

    private AuditState auditState;

    @Column(name = "param_value", length = 120)
    private String paramValue;

    @Column(name = "parameter_state", nullable = false, length = 30)
    private String parameterState;

    /**
     * Default constructor
     */
    public Parameter() {
        //Default
    }

    /**
     *
     * @param context
     * @param code
     * @param value
     */
    public Parameter(String context, String code, String value) {
        this.id = new ParameterPK(context, code);
        this.paramValue = value;
    }

    /**
     * Gets auditState.
     *
     * @return Value of auditState.
     */
    public AuditState getAuditState() {
        return auditState;
    }

    /**
     * Sets new auditState.
     *
     * @param auditState New value of auditState.
     */
    public void setAuditState(AuditState auditState) {
        this.auditState = auditState;
    }

    /**
     * Gets id.
     *
     * @return Value of id.
     */
    public ParameterPK getId() {
        return id;
    }

    /**
     * Sets new paramValue.
     *
     * @param paramValue New value of paramValue.
     */
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    /**
     * Sets new parameterState.
     *
     * @param parameterState New value of parameterState.
     */
    public void setParameterState(String parameterState) {
        this.parameterState = parameterState;
    }

    /**
     * Gets parameterState.
     *
     * @return Value of parameterState.
     */
    public String getParameterState() {
        return parameterState;
    }

    /**
     * Gets paramValue.
     *
     * @return Value of paramValue.
     */
    public String getParamValue() {
        return paramValue;
    }

    /**
     * Sets new id.
     *
     * @param id New value of id.
     */
    public void setId(ParameterPK id) {
        this.id = id;
    }
}