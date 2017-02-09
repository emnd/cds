package com.opengroup.res.core.domain;


import com.opengroup.res.core.domain.DomainException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.opengroup.res.core.domain.DomainAutorisation;
/**
 * Collaborator model Class
 *
 * @author Open group
 * @since 1.0.0
 */
public class DomainCollaborator extends DomainBeanTrackable implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private String loginOpen;
    private String lastName;
    private String firstName;
    private String emailOpen;
    private String buOpen;
    private List<DomainAutorisation> domainAuthorisationList = new ArrayList<DomainAutorisation>();

    /**
     * @param emailOpen
     * @param loginOpen
     * @param lastName
     * @param firstName
     * @param buOpen
     * @param autorisationList
     */
    public DomainCollaborator(String loginOpen, String lastName,String firstName, String emailOpen, String buOpen) throws DomainException {

        if (StringUtils.isEmpty(emailOpen)) {
            constraintsErrors.add("An email must be provided");
        }

        if (StringUtils.isEmpty(loginOpen)) {
            constraintsErrors.add("A login Open must be provided");
        }

        if (StringUtils.isEmpty(firstName)) {
            constraintsErrors.add("An firstName must be provided");
        }

        if (StringUtils.isEmpty(lastName)) {
            constraintsErrors.add("A lastName Open must be provided");
        }

        if (StringUtils.isEmpty(buOpen)) {
            constraintsErrors.add("A business Open must be provided");
        }

        checkIfValid();
        this.emailOpen = emailOpen;
        this.loginOpen = loginOpen;
        this.firstName = firstName;
        this.lastName = lastName;
        this.buOpen = buOpen;
    }

    public DomainCollaborator(String loginOpen, String lastName,String firstName, String emailOpen, String buOpen, Long id) throws DomainException {

        if (StringUtils.isEmpty(emailOpen)) {
            constraintsErrors.add("An email must be provided");
        }

        if (StringUtils.isEmpty(loginOpen)) {
            constraintsErrors.add("A login Open must be provided");
        }

        if (StringUtils.isEmpty(firstName)) {
            constraintsErrors.add("An firstName must be provided");
        }

        if (StringUtils.isEmpty(lastName)) {
            constraintsErrors.add("A lastName Open must be provided");
        }

        if (StringUtils.isEmpty(buOpen)) {
            constraintsErrors.add("A business Open must be provided");
        }

        checkIfValid();
        this.emailOpen = emailOpen;
        this.loginOpen = loginOpen;
        this.firstName = firstName;
        this.lastName = lastName;
        this.buOpen = buOpen;
        this.id = id;
    }

    /**
     * @param emailOpen
     * @param loginOpen
     * @param lastName
     * @param firstName
     * @param buOpen
     * @return
     */
    public static DomainCollaborator newInstance(String loginOpen, String lastName,String firstName, String emailOpen, String buOpen,Long id) throws DomainException {
        return new DomainCollaborator(loginOpen,lastName,firstName,emailOpen,buOpen, id);
    }

    public static DomainCollaborator updateInstance(String loginOpen, String lastName,String firstName, String emailOpen, String buOpen,Long id) throws DomainException {
        return new DomainCollaborator(loginOpen,lastName,firstName,emailOpen,buOpen, id);
    }

    public static DomainCollaborator deleteInstance(String loginOpen, String lastName,String firstName, String emailOpen, String buOpen,Long id) throws DomainException {
        return new DomainCollaborator(loginOpen,lastName,firstName,emailOpen,buOpen, id);
    }

    /**
     * Gets id.
     *
     * @return Value of id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets emailOpen.
     *
     * @return Value of emailOpen.
     */
    public String getEmailOpen() {
        return emailOpen;
    }

    /**
     * Gets loginOpen.
     *
     * @return Value of loginOpen.
     */
    public String getLoginOpen() {
        return loginOpen;
    }

    /**
     * Gets lastName.
     *
     * @return Value of lastName.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets firstName.
     *
     * @return Value of loginOpen.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets buOpen.
     *
     * @return Value of buOpen.
     */
    public String getBuOpen() {
        return buOpen;
    }

    /**
     * Gets domainAutorisationList.
     *
     * @return Value of domainAutorisationList.
     */
    public List<DomainAutorisation> getDomainAuthorisationList() {
        return domainAuthorisationList;
    }

    /**
     * Sets new id.
     *
     * @param id New value of id.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Sets new emailOpen.
     *
     * @param emailOpen New value of emailOpen.
     */
    public void setEmailOpen(String emailOpen) {
        this.emailOpen = emailOpen;
    }

    /**
     * Sets new loginOpen.
     *
     * @param loginOpen New value of loginOpen.
     */
    public void setLoginOpen(String loginOpen) {
        this.loginOpen = loginOpen;
    }

    /**
     * Sets new lastName.
     *
     * @param lastName New value of lastName.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets new firstName.
     *
     * @param firstName New value of firstName.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets new buOpen.
     *
     * @param buOpen New value of buOpen.
     */
    public void setBuOpen(String buOpen) {
        this.buOpen = buOpen;
    }

    /**
     * Sets new AutorisationList.
     *
     * @param AutorisationList New value of domainAutorisationList.
     */
    public void setAutorisationList(List<DomainAutorisation> domainAuthorisationList) {
        this.domainAuthorisationList = domainAuthorisationList;
    }
}
