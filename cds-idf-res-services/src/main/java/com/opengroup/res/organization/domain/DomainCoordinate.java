package com.opengroup.res.organization.domain;

import com.opengroup.res.core.domain.DomainBean;
import com.opengroup.res.core.domain.DomainException;
import org.springframework.util.StringUtils;

/**
 * Coordinate model Class
 *
 * @author Open group
 * @since 1.0.0
 */
public final class DomainCoordinate extends DomainBean {

    /**
     * 
	 * 
	 */
	private static final long serialVersionUID = 1L;  // le 15 fev 2017
	private String email;
    private String phoneNumber;

    /**
     * @param email
     * @param phoneNumber
     */
    private DomainCoordinate(String email, String phoneNumber) throws DomainException {

        if (StringUtils.isEmpty(email)) {
            constraintsErrors.add("An email must be provided");
        }

        if (StringUtils.isEmpty(phoneNumber)) {
            constraintsErrors.add("A phone number must be provided");
        }

        checkIfValid();
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    /**
     * @param email
     * @param phoneNumber
     * @return
     */
    public static DomainCoordinate newInstance(String email, String phoneNumber) throws DomainException {
        return new DomainCoordinate(email, phoneNumber);
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
     * Gets phoneNumber.
     *
     * @return Value of phoneNumber.
     */
    public String getPhoneNumber() {
        return phoneNumber;
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
     * Sets new phoneNumber.
     *
     * @param phoneNumber New value of phoneNumber.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
