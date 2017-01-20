package com.opengroup.res.ldap.entities;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;
import java.io.Serializable;
import java.util.List;

/**
 * A Spring LDAP Plain Old Java Object class bound with a Person
 *
 * @author Open groupe
 * @since 1.0.0
 */
@Entry(objectClasses = {"top", "groupOfUniqueNames"})
public final class Permission implements Serializable {

    @Id
    private Name distinguishName;

    @Attribute(name = "cn")
    private String commonName;

    @Attribute(name = "uniqueMember")
    private List<String> uniqueMembers;

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
     * Gets uniqueMembers.
     *
     * @return Value of uniqueMembers.
     */
    public List<String> getUniqueMembers() {
        return uniqueMembers;
    }

    /**
     * Sets new uniqueMembers.
     *
     * @param uniqueMembers New value of uniqueMembers.
     */
    public void setUniqueMembers(List<String> uniqueMembers) {
        this.uniqueMembers = uniqueMembers;
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
