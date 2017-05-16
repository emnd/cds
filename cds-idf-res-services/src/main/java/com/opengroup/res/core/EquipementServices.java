package com.opengroup.res.core;

import com.opengroup.res.core.domain.DomainAutorisation;
import com.opengroup.res.core.domain.DomainCollaborator;
import com.opengroup.res.core.domain.DomainEquipement;
import com.opengroup.res.core.domain.DomainEquipement.EquipementType;
import com.opengroup.res.core.domain.DomainEquipement.StateType;
import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.core.domain.DomainLocation;

import java.util.Date;
import java.util.List;
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
     * @param stationNameEquipement
     * @param serialNumberEquipement
     * @param markEquipement
     * @param markEquipement
     * @param modelEquipement
     * @throws DomainException
     */
    void createEquipement(String stationNameEquipement, String serialNumberEquipement, String markEquipement, String modelEquipement, Date attributionDateEquipement, Date returnDateEquipement, Date purchaseDateEquipement, Date expectedDateEquipement, String commentsEquipement, DomainEquipement.EquipementType equipementTypeEquipement, DomainEquipement.StateType stateTypeEquipement,DomainLocation domainLocation,DomainCollaborator domainCollaborator) throws DomainException;

    void createEquipementWithOutCollab(String stationNameEquipement, String serialNumberEquipement, String markEquipement, String modelEquipement, Date attributionDateEquipement, Date returnDateEquipement, Date purchaseDateEquipement, Date expectedDateEquipement, String commentsEquipement, DomainEquipement.EquipementType equipementTypeEquipement, DomainEquipement.StateType stateTypeEquipement,DomainLocation domainLocation) throws DomainException;
    /**
     * @param stationNameEquipement
     * @param serialNumberEquipement
     * @param markEquipement
     * @param markEquipement
     * @param modelEquipement
     * @throws DomainException
     */
    void updateEquipement(Long id, String stationNameEquipement, String serialNumberEquipement, String markEquipement, String modelEquipement, Date attributionDateEquipement, Date returnDateEquipement, Date purchaseDateEquipement, Date expectedDateEquipement, String commentsEquipement, DomainEquipement.EquipementType equipementTypeEquipement, DomainEquipement.StateType stateTypeEquipement, DomainLocation domainLocation,DomainCollaborator domainCollaborator) throws DomainException;
    void updateEquipementWithOutCollab(Long id, String stationNameEquipement, String serialNumberEquipement, String markEquipement, String modelEquipement, Date attributionDateEquipement, Date returnDateEquipement, Date purchaseDateEquipement, Date expectedDateEquipement, String commentsEquipement, DomainEquipement.EquipementType equipementTypeEquipement, DomainEquipement.StateType stateTypeEquipement, DomainLocation domainLocation) throws DomainException;


    /**/
    void deleteEquipement(Long id/*, String stationNameEquipement, String serialNumberEquipement, String markEquipement, String modelEquipement, Date attributionDateEquipement, Date returnDateEquipement, Date purchaseDateEquipement, Date expectedDateEquipement, String commentsEquipement, DomainEquipement.EquipementType equipementTypeEquipement, DomainEquipement.StateType stateTypeEquipement, DomainLocation domainLocation,DomainCollaborator domainCollaborator*/) throws DomainException;
    //  void deleteEquipementWithOutCollab(Long id, String stationNameEquipement, String serialNumberEquipement, String markEquipement, String modelEquipement, Date attributionDateEquipement, Date returnDateEquipement, Date purchaseDateEquipement, Date expectedDateEquipement, String commentsEquipement, DomainEquipement.EquipementType equipementTypeEquipement, DomainEquipement.StateType stateTypeEquipement, DomainLocation domainLocation)  throws DomainException;
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

    DomainEquipement findOne(Long id) throws DomainException;
    public DomainEquipement findEquipement(Long id) throws DomainException;
    public List<DomainEquipement> fullListAllWithoutCollab() throws DomainException;



    public Long getNbTotalPC() throws DomainException;

    public Long getNbTotalPCDesktop() throws DomainException;

    public Long getNbTotalPCLaptop() throws DomainException;

	/*public List<String> listEqType() throws DomainException;

	public List<String> listStateType() throws DomainException;*/
}
