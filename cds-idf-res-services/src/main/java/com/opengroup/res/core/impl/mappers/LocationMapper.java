package com.opengroup.res.core.impl.mappers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.opengroup.res.core.domain.DomainCollaborator;
import com.opengroup.res.core.domain.DomainEquipement;
import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.core.domain.DomainLocation;
import com.opengroup.res.core.domain.DomainParameter;
import com.opengroup.res.jpa.entities.Collaborator;
import com.opengroup.res.jpa.entities.Equipement;
import com.opengroup.res.jpa.entities.Location;
import com.opengroup.res.jpa.entities.Parameter;
import com.opengroup.res.util.AbstractDomainMapper;
/**
 * 
 * A parameter mapper
 */
@Component
public class LocationMapper extends AbstractDomainMapper<DomainLocation, Location> {
	
public DomainLocation toOneDomain(Location location) throws DomainException {
        
    	if(location == null){
			return null;
		}
		
    	DomainLocation domainLocation= new DomainLocation(location.getBlock(),location.getName(),location.getPlace(),location.getId());
        return  domainLocation;
    	
    }

  
    public Location toOneEntity(DomainLocation domainLocation) {
       // Date now = new Date();
        
        Location location = new Location();
        
        		
        location.setId(domainLocation.getId());
            
       location.setBlock(domainLocation.getBlockLocation());
        location.setName(domainLocation.getNameLocation());
        location.setPlace(domainLocation.getPlaceLocation());
       
		
        return location;
    }


    
	 
	public List<Location> convertDomainListToEntityList(List<DomainLocation> listDomain) {

		if (listDomain == null) {

			return null;
		}

		List<Location> listEntity = new ArrayList<Location>();

		for (DomainLocation domainLocation : listDomain) {

			listEntity.add(toOneEntity(domainLocation));
		}

		return listEntity;
	}
    
    
    public List<DomainLocation> convertEntityListToDomainList(List<Location> listEntity) throws DomainException {

		if (listEntity == null) {

			return null;
		}

		List<DomainLocation> listDomain = new ArrayList<DomainLocation>();

		for (Location Location : listEntity) {

			listDomain.add(toOneDomain(Location));
		}

		return listDomain;
	}
}
