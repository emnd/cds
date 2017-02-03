package com.opengroup.res.core.impl;

import com.opengroup.res.core.LocationServices;
import com.opengroup.res.core.ParameterServices;
import com.opengroup.res.core.domain.DomainEquipement;
import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.core.domain.DomainHistoryLog;
import com.opengroup.res.core.domain.DomainLocation;
import com.opengroup.res.core.domain.DomainParameter;
import com.opengroup.res.core.impl.mappers.LocationMapper;
import com.opengroup.res.core.impl.mappers.ParameterMapper;
import com.opengroup.res.jpa.HistoryLogRepository;
import com.opengroup.res.jpa.LocationRepository;
import com.opengroup.res.jpa.ParameterRepository;
import com.opengroup.res.jpa.entities.AuditState;
import com.opengroup.res.jpa.entities.Equipement;
import com.opengroup.res.jpa.entities.HistoryLog;
import com.opengroup.res.jpa.entities.Location;
import com.opengroup.res.jpa.entities.Parameter;
import com.opengroup.res.jpa.entities.ParameterPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
        DomainLocation domainLocation = DomainLocation.newCreatedStateInstance(nameLocation,  blockLocation, placeLocation);
        locationRepository.save(locationMapper.toOneEntity(domainLocation));
        logTrackLocation(now,"DEFAULT CREATION MESSAGE", DomainHistoryLog.newLocationInstance(domainLocation));
    }


    @Transactional
    public void updateLocation(Long idLocation, String nameLocation, String blockLocation, String placeLocation) throws DomainException {
        Date now = new Date();
        DomainLocation domainLocation= DomainLocation.newModifiedStateInstance(nameLocation,  blockLocation, placeLocation);
        Location Location = locationRepository.findOne(domainLocation.getIdLocation());
        if (Location == null) {
            throw new DomainException("This Equipement does not exist");
        }
        Location.setBlock(domainLocation.getBlockLocation());
		Location.setName(domainLocation.getNameLocation());
		Location.setPlace(domainLocation.getPlaceLocation());
		
		
		// debut le 28/12/2016
		
	//	Equipement.setLocation(locationMapper.toOneEntity(domainEquipement.getdomainLocation()));
        
        logTrackLocation(now,  "DEFAULT UPDATE MESSAGE", DomainHistoryLog.newLocationInstance(domainLocation));
        locationRepository.save(Location);
    }

 
    @Transactional
    public void deleteLocation(Long id) throws DomainException {
        Date now = new Date();
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

    /**
     * Track an history log - Can be provide as an internal transactional service
     * @param now
     * @param message
     * @param domainHistoryLog
     */
    private void logTrackLocation(Date now, String message, DomainHistoryLog domainHistoryLog) {
        HistoryLog historyLog = new HistoryLog(domainHistoryLog.getDynRootContextName(),
                domainHistoryLog.getSourceId(),
                now,
                domainHistoryLog.getUserId(),
                domainHistoryLog.getNewState(),
                domainHistoryLog.getMessage());

        historyLog.setMessage(message);
        historyLogRepository.save(historyLog);
    }

    @Override
    @Transactional
    public Set<DomainLocation> listAll() throws DomainException {
        return new HashSet<>(locationMapper.toDomains(locationRepository.findAll()));
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


	
}