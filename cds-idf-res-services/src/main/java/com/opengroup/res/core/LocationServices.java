package com.opengroup.res.core;

import com.opengroup.res.core.domain.DomainLocation;
import com.opengroup.res.jpa.entities.Location;
import com.opengroup.res.core.domain.DomainEquipement;
import com.opengroup.res.core.domain.DomainException;

import java.util.List;
import java.util.Set;
/**
 * Define the API to manipulate parameter
 *
 * @author Open groupe
 * @since 1.0.0
 */
public interface LocationServices {

    /**
     * Create and store a new parameter in the system, with the initial state and the owner of the creation step
     *
     * @param nameLocation
     * @param blockLocation
     * @param placeLocation
     * @param id
     * @throws DomainException
     */
    void createLocation(String nameLocation, String blockLocation, String placeLocation) throws DomainException;

    /**
     * @param nameLocation
     * @param blockLocation
     * @param placeLocation
     * @throws DomainException
     */
    void updateLocation( String nameLocation, String blockLocation, String placeLocation,Long id) throws DomainException;


    /**
     * @param nameLocation
     * @param blockLocation
     * @param placeLocation
     * @param id
     * @throws DomainException
     */
    void deleteLocation(Long id) throws DomainException;


    public DomainLocation findLocation(Long id) throws DomainException;
    /**
     * List all parameters of the system
     *
     * @return List<Parameter>
     * @throws DomainException
     */
    Set<DomainLocation> listAll() throws DomainException;

    /**
     * A generic creation api method
     * @param typedLocation
     * @param <T> A specialized parameter
     * @throws DomainException
     */
    <T extends DomainLocation> void createLocation(T typedLocation) throws DomainException;

    /**
     * A generic update api method
     * @param typedLocation
     * @param <T> A specialized parameter
     * @throws DomainException
     */
    <T extends DomainLocation> void updateLocation(T typedLocation) throws DomainException;

    /**
     * A generic delete api method
     * @param typedLocation
     * @param <T> A specialized parameter
     * @throws DomainException
     */
    <T extends DomainLocation> void deleteLocation(T typedLocation) throws DomainException;


    DomainLocation findOne(Long id) throws DomainException;

    Location findLocation(String name, String block, String place) throws DomainException;
    public List<DomainLocation> fullListAll() throws DomainException;
}
