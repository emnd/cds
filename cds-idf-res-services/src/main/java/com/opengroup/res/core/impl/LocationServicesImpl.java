package com.opengroup.res.core.impl;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opengroup.res.core.LocationServices;
import com.opengroup.res.core.domain.DomainEquipement;
//import com.opengroup.res.core.domain.DomainEquipement;
import com.opengroup.res.core.domain.DomainException;
//import com.opengroup.res.core.domain.DomainHistoryLog;
import com.opengroup.res.core.domain.DomainLocation;
import com.opengroup.res.core.impl.mappers.LocationMapper;
import com.opengroup.res.jpa.HistoryLogRepository;
import com.opengroup.res.jpa.LocationRepository;
import com.opengroup.res.jpa.entities.Collaborator;
import com.opengroup.res.jpa.entities.Equipement;
import com.opengroup.res.jpa.entities.Location;
/**
 * Implementation to manage Parameter service
 *
 * @author Open groupe
 * @since 1.0.0
 */
@Service
public class LocationServicesImpl implements LocationServices {
    @Autowired
    private HistoryLogRepository historyLogRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocationMapper locationMapper;


    @Transactional
    public void createLocation(String nameLocation, String blockLocation, String placeLocation) throws DomainException {
        Date now = new Date();
        Long id = null; 
        DomainLocation domainLocation = DomainLocation.newCreatedStateInstance(nameLocation,  blockLocation, placeLocation,id);
        locationRepository.save(locationMapper.toOneEntity(domainLocation));
        //logTrackLocation(now,"DEFAULT CREATION MESSAGE", DomainHistoryLog.newLocationInstance(domainLocation));
    }


    @Transactional
    public void updateLocation(String nameLocation, String blockLocation, String placeLocation,Long id ) throws DomainException {
        Date now = new Date();
        DomainLocation domainLocation= DomainLocation.newModifiedStateInstance(nameLocation,  blockLocation, placeLocation,id);
        Location location = locationRepository.findOne(domainLocation.getId());
        if (location == null) {
            throw new DomainException("This Equipement does not exist");
        }
        location.setBlock(domainLocation.getBlockLocation());
		location.setName(domainLocation.getNameLocation());
		location.setPlace(domainLocation.getPlaceLocation());
		
		
		// debut le 28/12/2016
		
	//	Equipement.setLocation(locationMapper.toOneEntity(domainEquipement.getdomainLocation()));
        
       
    }

 
    @Transactional
    public void deleteLocation(Long id) throws DomainException {
        //Date now = new Date();
       /* DomainLocation domainLocation = DomainLocation.newSuppressedStateInstance(nameLocation,  blockLocation, placeLocation);
        Location location = new Location(domainLocation.getNameLocation().toString(),
                                            domainLocation.getBlockLocation(),
                                            domainLocation.getPlaceLocation());

        Location existingLocation = locationRepository.findOne(location.getId());
        if (existingLocation == null) {
            throw new DomainException("This location has not be found");
        } else {
            logTrackLocation(now, "DEFAULT DELETE MESSAGE", DomainHistoryLog.newLocationInstance(domainLocation));
            locationRepository.delete(existingLocation);
        }
*/
        Location existingLocation = locationRepository.findOne(id);

       
         if (existingLocation  == null) {
             throw new DomainException("This location has not be found");
         } else {

        	 locationRepository.delete(existingLocation);
         }
                
    }
    
    @Transactional
    public DomainLocation findLocation(Long id) throws DomainException
    {
    	Location location = new Location();
    	location = locationRepository.findOne(id);
    	
    	DomainLocation domainLocation = new DomainLocation(location.getBlock(),location.getName(),location.getPlace(),location.getId());
    	domainLocation = locationMapper.toOneDomain(location);
    	
    	return domainLocation;
    }
    
    @Override
    @Transactional
    public Location findLocation(String name,String block, String place) throws DomainException {  
		Location location = new Location();
		List<Location>	locationList = locationRepository.findByNameAndBlockAndPlace(name,block, place);
		for(Location loc : locationList)
		{
			location = loc;
		}

			return location;
		
	}

    /**
     * Track an history log - Can be provide as an internal transactional service
     * @param now
     * @param message
     * @param domainHistoryLog
     */
  /*private void logTrackLocation(Date now, String message, DomainHistoryLog domainHistoryLog) {
        HistoryLog historyLog = new HistoryLog(domainHistoryLog.getDynRootContextName(),
                domainHistoryLog.getSourceId(),
                now,
                domainHistoryLog.getUserId(),
                domainHistoryLog.getNewState(),
                domainHistoryLog.getMessage());

        historyLog.setMessage(message);
        historyLogRepository.save(historyLog);
    }*/

    @Override
    @Transactional
    public Set<DomainLocation> listAll() throws DomainException {
        return new HashSet<>(locationMapper.toDomains(locationRepository.findAll()));
    }

    @Override
    @Transactional
    public List<DomainLocation> fullListAll() throws DomainException {
    	List<Location> locations = locationRepository.findAll();
    	List<DomainLocation> domainLocations = locationMapper.convertEntityListToDomainList(locations);
        return domainLocations;
    }
	

	@Override
	public <T extends DomainLocation> void createLocation(T typedLocation) throws DomainException {
		// TODO Auto-generated method stub
		
	}

	
	
	@Override
	public <T extends DomainLocation> void updateLocation(T typedLocation) throws DomainException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T extends DomainLocation> void deleteLocation(T typedLocation) throws DomainException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public DomainLocation findOne(Long id) throws DomainException {
		Location location = locationRepository.findOne(id);
    	DomainLocation domainLocation=	locationMapper.toOneDomain(location);
    	return domainLocation;
		
	}


	


	
}