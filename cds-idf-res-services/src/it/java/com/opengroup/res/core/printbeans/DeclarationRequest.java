package com.opengroup.res.core.printbeans;

import com.opengroup.res.core.domain.DomainException;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 *DeclarationRequest class. This class is used for Reporting Example
 */
public class DeclarationRequest {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(DeclarationRequest.class);
    //Site d'exploitation du demandeur
    private String siteExpl;

    //Numero de parcelle
    private Integer parcelNumber;

    //Quantit� livr�e
    private Float delivredQuantity;

    //Zone tarifaire
    private Integer tariffZone;

    //Site de livraison
    private String livraisonPlace;

    /**
     *Constructor without parameters
     */
    private DeclarationRequest() {
        super();
    }

    /**
     * Constructor with parameters
     * @param siteExpl
     * @param parcelNumber
     * @param delivredQuantity
     * @param tariffZone
     * @param livraisonPlace
     */

    private DeclarationRequest(String siteExpl, Integer parcelNumber, Float delivredQuantity, Integer tariffZone, String livraisonPlace) {
        this.siteExpl = siteExpl;
        this.parcelNumber = parcelNumber;
        this.delivredQuantity = delivredQuantity;
        this.tariffZone = tariffZone;
        this.livraisonPlace = livraisonPlace;
    }

    /**
     * Method that return a new instance of DeclarationRequest
     * @param siteExpl
     * @param parcelNumber
     * @param delivredQuantity
     * @param tariffZone
     * @param livraisonPlace
     * @return
     * @throws DomainException
     */
    public static DeclarationRequest newInstance(String siteExpl, Integer parcelNumber,
                                                 Float delivredQuantity, Integer tariffZone, String livraisonPlace) throws DomainException {
        if (StringUtils.isEmpty(siteExpl) || parcelNumber == null || delivredQuantity == null || tariffZone == null || StringUtils.isEmpty(livraisonPlace)) {
            throw new DomainException("Can't find identities");
        }
        LOGGER.info("Site d'exploitation : " + siteExpl);
        LOGGER.info("Numero de parcel : " + parcelNumber);
        LOGGER.info("Quantite livre : " + delivredQuantity);
        LOGGER.info("Zone tarifaire : " + tariffZone);
        LOGGER.info("Lieu de livraison : " + livraisonPlace);

        return new DeclarationRequest(siteExpl, parcelNumber, delivredQuantity, tariffZone, livraisonPlace);
    }

    /**
     * Getters
     */
    public String getSiteExpl() {
        return siteExpl;
    }

    public Integer getParcelNumber() {
        return parcelNumber;
    }

    public Float getDelivredQuantity() {
        return delivredQuantity;
    }

    public Integer getTariffZone() {
        return tariffZone;
    }

    public String getLivraisonPlace() {
        return livraisonPlace;
    }
}