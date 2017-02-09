package com.opengroup.res.core;

import com.opengroup.res.core.domain.DomainEquipement;
import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.enums.EquipementType;
import com.opengroup.res.enums.StateType;

import java.util.Date;
import java.util.Set;

/**
 * Define the API to manipulate Equipement
 *
 * @author Open groupe
 * @since 1.0.0
 */
public interface EquipementServices {

    /**
     * Create and store a new Equipement in the system, with the initial state and the owner of the creation step
     *
     * @param context
     * @param code
     * @param value
     * @param userId
     * @throws DomainException
     */
    void createEquipement(String stationNameEquipement, String serialNumberEquipement, String markEquipement, String modelEquipement, Date attributionDateEquipement, Date returnDateEquipement, Date purchaseDateEquipement, Date expectedDateEquipement, String commentsEquipement, EquipementType equipementTypeEquipement, StateType stateTypeEquipement) throws DomainException;

    /**
     * @param context
     * @param code
     * @param value
     * @param userId
     * @throws DomainException
     */
    void updateEquipement(Long idEquipement, String stationNameEquipement, String serialNumberEquipement, String markEquipement, String modelEquipement, Date attributionDateEquipement, Date returnDateEquipement, Date purchaseDateEquipement, Date expectedDateEquipement, String commentsEquipement, EquipementType equipementTypeEquipement, StateType stateTypeEquipement) throws DomainException;


    /**
     * @param context
     * @param code
     * @param userId
     * @throws DomainException
     */
    void deleteEquipement(Long idEquipement, String stationNameEquipement, String serialNumberEquipement, String markEquipement, String modelEquipement, Date attributionDateEquipement, Date returnDateEquipement, Date purchaseDateEquipement, Date expectedDateEquipement, String commentsEquipement, EquipementType equipementTypeEquipement, StateType stateTypeEquipement) throws DomainException;

    /**
     * List all Equipements of the system
     *
     * @return List<Equipement>
     * @throws DomainException
     */
    Set<DomainEquipement> listAll() throws DomainException;

    /**
     * A generic creation api method
     * @param typedEquipement
     * @param <T> A specialized Equipement
     * @throws DomainException
     */
    <T extends DomainEquipement> void createEquipement(T typedEquipement) throws DomainException;

    /**
     * A generic update api method
     * @param typedEquipement
     * @param <T> A specialized Equipement
     * @throws DomainException
     */
    <T extends DomainEquipement> void updateEquipement(T typedEquipement) throws DomainException;

    /**
     * A generic delete api method
     * @param typedEquipement
     * @param <T> A specialized Equipement
     * @throws DomainException
     */
    <T extends DomainEquipement> void deleteEquipement(T typedEquipement) throws DomainException;
}