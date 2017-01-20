package com.opengroup.res.core.printbeans;


import com.opengroup.res.core.domain.DomainException;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 *Applicant class. This class is used for Reporting Example
 */
public class Applicant {

    /**
     * Applicant attributs
     */
    private String nom;
    private String adresse;
    private String codePostal;
    private String commune;
    private String tel;
    private List<DetailsApplicant> detailsDemande;
    private List<DeclarationRequest> declarationDemande;

    /**
     * Constructor without parameters
     */
    private Applicant() {
        super();
        //Default
    }

    /**
     *Constructor with parameters
     * @param nom
     * @param adresse
     * @param codePostal
     * @param commune
     * @param tel
     * @param declarationDemande
     * @param detailsDemande
     */
    private Applicant(String nom, String adresse, String codePostal, String commune, String tel,
                     List<DeclarationRequest> declarationDemande, List<DetailsApplicant> detailsDemande) {
        super();
        this.nom = nom;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.commune = commune;
        this.tel = tel;
        this.declarationDemande = declarationDemande;
        this.detailsDemande = detailsDemande;
    }

    /**
     *
     * @param nom
     * @param adresse
     * @param codePostal
     * @param commune
     * @param tel
     * @param declarationDemande
     * @param detailsDemande
     * @return
     * @throws DomainException
     */
    public static Applicant newInstance(String nom, String adresse, String codePostal, String commune, String tel,
                                        List<DeclarationRequest> declarationDemande, List<DetailsApplicant> detailsDemande) throws DomainException {

        if (StringUtils.isEmpty(nom) || StringUtils.isEmpty(adresse) || StringUtils.isEmpty(commune) || StringUtils.isEmpty(tel) || StringUtils.isEmpty(declarationDemande) || StringUtils.isEmpty(detailsDemande)) {
            throw new DomainException("Can't find identities");
        }

        return Applicant.newInstance(nom, adresse, codePostal, commune, tel, declarationDemande, detailsDemande);
    }


    /**
     * Gets detailsDemande.
     *
     * @return Value of detailsDemande.
     */
    public List<DetailsApplicant> getDetailsDemande() {
        return detailsDemande;
    }

    /**
     * Gets tel.
     *
     * @return Value of tel.
     */
    public String getTel() {
        return tel;
    }

    /**
     * Gets declarationDemande.
     *
     * @return Value of declarationDemande.
     */
    public List<DeclarationRequest> getDeclarationDemande() {
        return declarationDemande;
    }

    /**
     * Gets Applicant attributs.
     *
     * @return Value of Applicant attributs.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Gets adresse.
     *
     * @return Value of adresse.
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Gets codePostal.
     *
     * @return Value of codePostal.
     */
    public String getCodePostal() {
        return codePostal;
    }

    /**
     * Gets commune.
     *
     * @return Value of commune.
     */
    public String getCommune() {
        return commune;
    }
}
