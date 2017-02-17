package com.opengroup.res.core.impl.mappers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.core.domain.DomainLocation;
import com.opengroup.res.core.domain.DomainParameter;
import com.opengroup.res.jpa.entities.Location;
import com.opengroup.res.jpa.entities.Parameter;
import com.opengroup.res.util.AbstractDomainMapper;

/**
 * A parameter mapper
 */
@Component
public class LocationMapper {

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


	
    
	 public List<DomainLocation> toDomains(Iterable<Location> iterable) throws DomainException{
	        List<DomainLocation> domains = new ArrayList<>();
	        if(iterable != null){
	            for(Location bean : iterable){
	                domains.add(toOneDomain(bean));
	            }
	        }
	        return domains;
	    }
    
	 
    
}
