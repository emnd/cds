package com.opengroup.res.organization.domain;

import com.opengroup.res.core.domain.DomainBean;
import com.opengroup.res.core.domain.DomainException;
import org.springframework.util.StringUtils;

/**
 * A class defining what an identity is.
 *
 * @author Open group
 * @since 1.0.0
 */
public final class DomainIdentity extends DomainBean {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String identifier;
    private String commonName;
    private String surname;
    private String givenName;
    private String mail;   // le 15 fev 2017

    private DomainIdentity(String identifier, String commonName, String surname, String givenName,String mail) throws DomainException {

        if (StringUtils.isEmpty(identifier)) {
            constraintsErrors.add("User can't be create without an identifier");
        }

        if (StringUtils.isEmpty(commonName)) {
            constraintsErrors.add("User can't be create without a common name");
        }

        if (StringUtils.isEmpty(surname)) {
            constraintsErrors.add("User can't be create without a surname");
        }

        if (StringUtils.isEmpty(givenName)) {
            constraintsErrors.add("User can't be create without a given name");
        }

        if (StringUtils.isEmpty(mail)) {
            constraintsErrors.add("User can't be create without a given email");
        }
        checkIfValid();
        this.identifier = identifier;
        this.commonName = commonName;
        this.surname = surname;
        this.givenName = givenName;
        this.mail = mail; // le 15 fev 2017
    }

    /**
     * Create a valid identity instance
     *
     * @param identifier
     * @param commonName
     * @param surname
     * @param givenName
     * @return
     * @throws DomainException
     */
    public static DomainIdentity newInstance(String identifier, String commonName, String surname, String givenName, String mail) throws DomainException {
        return new DomainIdentity(identifier, commonName, surname, givenName,mail);
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
     * Gets surname.
     *
     * @return Value of surname.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Gets givenName.
     *
     * @return Value of givenName.
     */
    public String getGivenName() {
        return givenName;
    }

    /**
     * Gets identifier.
     *
     * @return Value of identifier.
     */
    public String getIdentifier() {
        return identifier;
    }
    
    public String getMail()
    {
    	return mail;
    }
}
