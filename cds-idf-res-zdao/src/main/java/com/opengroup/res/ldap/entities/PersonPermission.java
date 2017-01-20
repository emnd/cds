package com.opengroup.res.ldap.entities;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;
import java.io.Serializable;

/**
 * A Spring LDAP Plain Old Java Object class bound with a Person
 *
 * @author Open groupe
 * @since 1.0.0
 */
@Entry(objectClasses = {"top", "groupOfUniqueNames"})
public final class PersonPermission implements Serializable {

    @Id
    private Name distinguishName;

    @Attribute(name = "cn")
    private String commonName;

    /**
     * Gets commonName.
     *
     * @return Value of commonName.
     */
    public String getCommonName() {
        return commonName;
    }

    /**
     * Sets new commonName.
     *
     * @param commonName New value of commonName.
     */
    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    /**
     * Gets distinguishName.
     *
     * @return Value of distinguishName.
     */
    public Name getDistinguishName() {
        return distinguishName;
    }

    /**
     * Sets new distinguishName.
     *
     * @param distinguishName New value of distinguishName.
     */
    public void setDistinguishName(Name distinguishName) {
        this.distinguishName = distinguishName;
    }
}
