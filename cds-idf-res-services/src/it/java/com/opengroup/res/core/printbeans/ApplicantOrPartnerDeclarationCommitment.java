package com.opengroup.res.core.printbeans;

import com.opengroup.res.core.domain.DomainException;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

/**
 * Class qui represente une declaration et engagement du demandeur ou d'un associe
 * Created by nabil on 31/10/2016.
 */
public class ApplicantOrPartnerDeclarationCommitment {

    /**
     * site dexploitation
     */
    private String siteExploitation;

    /**
     * N° de parcelle
     */
    private BigDecimal numeroParcelle;

    /**
     * quantitee livree (t)
     */
    private BigDecimal quantiteLivree;

    /**
     * Zone tarifaire
     */
    private String zoneTarifaire;

    /**
     * Site de livraison
     */
    private String siteLivraison;

    /**
     * Constructeur privee
     *
     * @param siteExploitation
     * @param numeroParcelle
     * @param quantiteLivree
     * @param zoneTarifaire
     * @param siteLivraison
     */
    private ApplicantOrPartnerDeclarationCommitment(String siteExploitation, BigDecimal numeroParcelle, BigDecimal quantiteLivree, String zoneTarifaire, String siteLivraison) {
        this.siteExploitation = siteExploitation;
        this.numeroParcelle = numeroParcelle;
        this.quantiteLivree = quantiteLivree;
        this.zoneTarifaire = zoneTarifaire;
        this.siteLivraison = siteLivraison;
    }

    /**
     * To create new Instacne of ApplicantOrPartnerDeclarationCommitment
     *
     * @param siteExploitation
     * @param numeroParcelle
     * @param quantiteLivree
     * @param zoneTarifaire
     * @param siteLivraison
     * @return ApplicantOrPartnerDeclarationCommitment
     */
    public static ApplicantOrPartnerDeclarationCommitment newInstacne(String siteExploitation, BigDecimal numeroParcelle, BigDecimal quantiteLivree, String zoneTarifaire, String siteLivraison) throws DomainException {

        if (StringUtils.isEmpty(siteExploitation) || StringUtils.isEmpty(zoneTarifaire) || StringUtils.isEmpty(siteLivraison)) {
            throw new DomainException("Must set all fields");
        }

        if (numeroParcelle == null || quantiteLivree == null) {

            throw new DomainException("Must set all fields");
        }
        return new ApplicantOrPartnerDeclarationCommitment(siteExploitation, numeroParcelle, quantiteLivree, zoneTarifaire, siteLivraison);
    }

    /**
     * Gets Site de livraison.
     *
     * @return Value of Site de livraison.
     */
    public String getSiteLivraison() {
        return siteLivraison;
    }

    /**
     * Gets Zone tarifaire.
     *
     * @return Value of Zone tarifaire.
     */
    public String getZoneTarifaire() {
        return zoneTarifaire;
    }

    /**
     * Gets quantitee livree t.
     *
     * @return Value of quantitee livree t.
     */
    public BigDecimal getQuantiteLivree() {
        return quantiteLivree;
    }

    /**
     * Gets N° de parcelle.
     *
     * @return Value of N° de parcelle.
     */
    public BigDecimal getNumeroParcelle() {
        return numeroParcelle;
    }

    /**
     * Gets site dexploitation.
     *
     * @return Value of site dexploitation.
     */
    public String getSiteExploitation() {
        return siteExploitation;
    }
}
