package com.opengroup.res.core.domain;

import java.io.Serializable;

/**
 * ODEADOM DomainParameter model. A parameter is identified by its context and its code and have a related value. Note that it's a generic solution to materialize very simple
 * data. When data representation properties are growing, think of a dedicated class object. Other tip : ff something is materialized in a parameter structure repository, you
 * can also define a type class object, it is design for extension .. So make the good choice ...
 *
 *
 * @author Open groupe
 * @since 1.0.0
 */
public class DomainParameter extends DomainBeanTrackable implements Serializable {

    /**
     * Define all possible parameter state managed by the application
     */
    public enum ParameterStatus{
        CREATED, MODIFIED, SUPPRESSED, VALIDATED
    }

    /**
     *
     */
    public enum ParameterContext{
        PRODUCT, STATUS
    }

    private ParameterContext context;

    private String key;

    private String value;

    private ParameterStatus state;

    /**
     * Domain parameter default constructor
     * @param context
     * @param key
     * @param value
     * @param userId
     * @param state
     */
    protected DomainParameter(ParameterContext context, String key, String value, String userId, ParameterStatus state) throws DomainException {
        super(userId);

        // Apply rules
        if(context == null) {
            constraintsErrors.add( "DomainParameter context unsatisfied rule" );
        }

        if(key == null) {
            constraintsErrors.add( "DomainParameter key unsatisfied rule" );
        }

        if(state == null) {
            constraintsErrors.add( "DomainParameter state unsatisfied rule" );
        }

        if(constraintsErrors.size() > 0){
            throw new DomainException("" );
        }

        this.context = context;
        this.key = key;
        this.value = value;
        this.state = state;

    }


    /**
     * An easiest factory method to created state instance
     * @param context
     * @param key
     * @param value
     * @param userId
     * @return
     * @throws DomainException
     */
    public static DomainParameter newCreatedStateInstance(ParameterContext context, String key, String value, String userId) throws DomainException {
        return new DomainParameter( context, key, value, userId, ParameterStatus.CREATED );
    }

    /**
     * An easiest factory method to modified state instance
     * @param context
     * @param key
     * @param value
     * @param userId
     * @return
     * @throws DomainException
     */
    public static DomainParameter newModifiedStateInstance(ParameterContext context, String key, String value, String userId)throws DomainException {
        return new DomainParameter( context, key, value, userId, ParameterStatus.MODIFIED );
    }

    /**
     * An easiest factory method to suppressed state instance
     * @param context
     * @param key
     * @param userId
     * @return
     * @throws DomainException
     */
    public static DomainParameter newSuppressedStateInstance(ParameterContext context, String key, String userId) throws DomainException {
        return new DomainParameter( context, key, null, userId, ParameterStatus.SUPPRESSED );
    }

    /**
     * An easiest factory method to validated state instance
     * @param context
     * @param key
     * @param value
     * @param userId
     * @return
     * @throws DomainException
     */
    public static DomainParameter newValidatedStateInstance(ParameterContext context, String key, String value, String userId) throws DomainException {
        return new DomainParameter( context, key, value, userId, ParameterStatus.VALIDATED );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DomainParameter)) {
            return false;
        }
        if (this.context != null && !this.context.equals(((DomainParameter) obj).getContext())) {
            return false;
        }
        if (this.key != null && !this.key.equals(((DomainParameter) obj).getKey())) {
            return false;
        }
        return !(this.value != null && !this.value.equals(((DomainParameter) obj).getValue()));
    }

    @Override
    public int hashCode() {
        int result = this.context != null ? this.context.hashCode() : 0;
        result = 31 * result + (this.context != null ? this.context.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    /**
     * Gets key.
     *
     * @return Value of key.
     */
    public String getKey() {
        return key;
    }

    /**
     * Gets context.
     *
     * @return Value of context.
     */
    public ParameterContext getContext() {
        return context;
    }

    /**
     * Gets state.
     *
     * @return Value of state.
     */
    public ParameterStatus getState() {
        return state;
    }

    /**
     * Gets value.
     *
     * @return Value of value.
     */
    public String getValue() {
        return value;
    }
}
