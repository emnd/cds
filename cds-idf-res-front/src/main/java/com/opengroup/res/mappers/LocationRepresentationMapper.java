package com.opengroup.res.mappers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.core.domain.DomainLocation;
import com.opengroup.res.model.LocationRepresentation;
/**
 * Parameter representation mapper
 *
 * @author Open group
 * @since 1.0.0
 */
@Component
public class LocationRepresentationMapper  {

     public DomainLocation toOneDomain(LocationRepresentation representation) throws DomainException {
    
        return new DomainLocation(representation.getNameLocation(),representation.getBlockLocation(),representation.getPlaceLocation(),representation.getId());
    }


    public LocationRepresentation toOneRepresentation(DomainLocation domain){
    	LocationRepresentation locationRepresentation = new LocationRepresentation();
    	locationRepresentation.setId(domain.getId());
    	locationRepresentation.setPlaceLocation(domain.getPlaceLocation());
    	locationRepresentation.setNameLocation(domain.getNameLocation());
    	locationRepresentation.setBlockLocation(domain.getBlockLocation());
        return locationRepresentation;
    }
    

	public List<DomainLocation> toDomains(Collection<LocationRepresentation> locationRepresentations) throws DomainException{
        List<DomainLocation> domains = new ArrayList<>();
        if(locationRepresentations != null){
            for(LocationRepresentation rep : locationRepresentations){
                domains.add(toOneDomain(rep));
            }
        }
        return domains;
    }

    
	
	
    public List<LocationRepresentation> toRepresentations(Collection<DomainLocation> domains) {
        List<LocationRepresentation> locationRepresentations = new ArrayList<>();
        if(domains != null){
            for(DomainLocation domain : domains){
            	locationRepresentations.add(toOneRepresentation(domain));
            }
        }
        return locationRepresentations ;
    } 
    
    
    
}
