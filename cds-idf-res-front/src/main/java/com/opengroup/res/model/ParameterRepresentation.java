package com.opengroup.res.model;

import com.opengroup.res.util.RepresentationBean;

/**
 * Parameter representation
 *
 * @author Open group
 * @since 1.0.0
 */
public class ParameterRepresentation implements RepresentationBean {

    private String context;

    private String key;

    private String value;

    private String state;


    /**
     * Sets new key.
     *
     * @param key New value of key.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Gets value.
     *
     * @return Value of value.
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets new context.
     *
     * @param context New value of context.
     */
    public void setContext(String context) {
        this.context = context;
    }

    /**
     * Sets new state.
     *
     * @param state New value of state.
     */
    public void setState(String state) {
        this.state = state;
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
     * Gets state.
     *
     * @return Value of state.
     */
    public String getState() {
        return state;
    }

    /**
     * Sets new value.
     *
     * @param value New value of value.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets key.
     *
     * @return Value of key.
     */
    public String getKey() {
        return key;
    }
}
