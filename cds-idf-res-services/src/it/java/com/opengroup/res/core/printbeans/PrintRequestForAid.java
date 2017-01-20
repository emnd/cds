package com.opengroup.res.core.printbeans;

import com.opengroup.res.core.domain.DomainException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Classe representation de PDF sur 3 pages de la demande aide
 * <p>
 * Created by nabil on 27/10/2016.
 */
public class PrintRequestForAid {


    /**
     * Nom - prenom ou raison social
     */
    private String identiteDemandeur;

    /**
     * Annee de recolte YYYY
     */
    private Date anneeRecolte;

    /**
     * Liste des declarations et engagements du demandeurs et de chacun des associes
     */
    private List<ApplicantOrPartnerDeclarationCommitment> declarationEngagementDemandeurAssocies;

    /**
     * Constructeur privee
     *
     * @param identiteDemandeur
     * @param anneeRecolte
     * @param declarationEngagementDemandeurAssocies
     */
    private PrintRequestForAid(String identiteDemandeur, Date anneeRecolte, List<ApplicantOrPartnerDeclarationCommitment> declarationEngagementDemandeurAssocies) {
        this.identiteDemandeur = identiteDemandeur;
        this.anneeRecolte = anneeRecolte;
        this.declarationEngagementDemandeurAssocies = declarationEngagementDemandeurAssocies;
    }

    /**
     * .
     * Create new Instance
     *
     * @param identiteDemandeur
     * @param anneeRecolte
     * @param declarationEngagementDemandeurAssocies
     * @return PrintRequestForAid
     * @throws DomainException
     */
    public static PrintRequestForAid newInstance(String identiteDemandeur, Date anneeRecolte, List<ApplicantOrPartnerDeclarationCommitment> declarationEngagementDemandeurAssocies) throws DomainException {

        if (StringUtils.isEmpty(identiteDemandeur) || anneeRecolte == null || CollectionUtils.isEmpty(declarationEngagementDemandeurAssocies)) {
            throw new DomainException("Sorry we need all fields");
        }
        return new PrintRequestForAid(identiteDemandeur, anneeRecolte, declarationEngagementDemandeurAssocies);
    }


    /**
     * Gets Annee de recolte YYYY.
     *
     * @return Value of Annee de recolte YYYY.
     */
    public Date getAnneeRecolte() {
        return anneeRecolte;
    }

    /**
     * Gets Nom - prenom ou raison social.
     *
     * @return Value of Nom - prenom ou raison social.
     */
    public String getidentiteDemandeur() {
        return identiteDemandeur;
    }

    /**
     * Gets Liste des declarations et engagements du demandeurs et de chacun des associes.
     *
     * @return Value of Liste des declarations et engagements du demandeurs et de chacun des associes.
     */
    public List<ApplicantOrPartnerDeclarationCommitment> getDeclarationEngagementDemandeurAssocies() {
        return declarationEngagementDemandeurAssocies;
    }
}
