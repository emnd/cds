package com.opengroup.res.jpa.entities;

import com.opengroup.res.util.EntityBean;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * The primary key class for the parameter database table.
 */
@Embeddable
public class ParameterPK implements Serializable, EntityBean {
    //default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, length = 64)
    private String context;

    @Column(nullable = false, length = 30)
    private String code;

    /**
     * Default parameter
     */
    public ParameterPK() {
        //Default
    }

    /**
     *
     * @param context
     * @param code
     */
    public ParameterPK(String context, String code) {
        this.context = context;
        this.code = code;
    }

    /**
     * Gets context.
     *
     * @return Value of context.
     */
    public String getContext() {
        return context;
    }

    /**
     * Sets new code.
     *
     * @param code New value of code.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets code.
     *
     * @return Value of code.
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets new context.
     *
     * @param context New value of context.
     */
    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ParameterPK)) {
            return false;
        }
        /**
         *
         */
        ParameterPK castOther = (ParameterPK) other;
        return
                this.context.equals(castOther.context)
                        && this.code.equals(castOther.code);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.context.hashCode();
        hash = hash * prime + this.code.hashCode();

        return hash;
    }
}