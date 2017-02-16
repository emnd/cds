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
@Entry(objectClasses = {"top", "person", "organizationalPerson", "inetOrgPerson"})
public final class Person implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    private Name distinguishName;

    @Attribute(name = "cn")
    private String commonName;

    @Attribute(name = "sn")
    private String name;

    @Attribute(name = "uid")
    private String uid;

    @Attribute(name = "givenName")
    private String givenName;

    @Attribute(name = "mail")
    private String email;

    /**
     * Gets givenName.
     *
     * @return Value of givenName.
     */
    public String getGivenName() {
        return givenName;
    }

    /**
     * Sets new givenName.
     *
     * @param givenName New value of givenName.
     */
    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    /**
     * Gets name.
     *
     * @return Value of name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets new name.
     *
     * @param name New value of name.
     */
    public void setName(String name) {
        this.name = name;
    }

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
     * Gets email.
     *
     * @return Value of email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets new email.
     *
     * @param email New value of email.
     */
    public void setEmail(String email) {
        this.email = email;
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

    /**
     * Gets uid.
     *
     * @return Value of uid.
     */
    public String getUid() {
        return uid;
    }

    /**
     * Sets new uid.
     *
     * @param uid New value of uid.
     */
    public void setUid(String uid) {
        this.uid = uid;
    }
}
