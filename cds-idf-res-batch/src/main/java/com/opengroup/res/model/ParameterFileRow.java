package com.opengroup.res.model;

/**
 * This class represents the row structure of the input file. It is just
 * a POJO consumed by a reader and transform by a processor
 *
 * @author Open group
 * @since 1.0.0
 */
public class ParameterFileRow {

    private String context;

    private String key;

    private String value;

    /**
     * Gets context.
     *
     * @return Value of context.
     */
    public String getContext() {
        return context;
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
     * Gets key.
     *
     * @return Value of key.
     */
    public String getKey() {
        return key;
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
     * Sets new key.
     *
     * @param key New value of key.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Sets new context.
     *
     * @param context New value of context.
     */
    public void setContext(String context) {
        this.context = context;
    }
}
