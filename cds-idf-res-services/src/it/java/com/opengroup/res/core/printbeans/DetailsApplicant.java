package com.opengroup.res.core.printbeans;


import com.opengroup.res.core.domain.DomainException;

import org.springframework.util.StringUtils;

import java.util.Date;

/**
 *DetailsApplicant class. This class is used for Reporting Example
 */
public class DetailsApplicant {
    /**
     *
     */
    private String etat;
    /**
     *
     */
    private Date dateVerification;

    /**
     *Constructor without parameters
     */
    private DetailsApplicant() {
        //Default
    }

    /**
     *Constructor with parameters
     * @param etat
     * @param dateVerification
     */
    private DetailsApplicant(String etat, Date dateVerification) {
        this.etat = etat;
        this.dateVerification = dateVerification;
    }

    /**
     * Method that return a new instance of DetailsApplicant
     * @param etat
     * @param dateVerification
     * @return
     * @throws DomainException
     */
    public static DetailsApplicant newInstance(String etat, Date dateVerification) throws DomainException {
        if (StringUtils.isEmpty(etat)) {
            new DomainException("Didn't find field");
        }

        return  DetailsApplicant.newInstance(etat, dateVerification);

    }

    public String getEtat() {
        return etat;
    }

    public Date getDateVerification() {
        return dateVerification;
    }


}
