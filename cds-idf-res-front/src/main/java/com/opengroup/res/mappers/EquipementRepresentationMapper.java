package com.opengroup.res.mappers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opengroup.res.core.domain.DomainEquipement;
import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.model.EquipementRepresentation;
/**
 * Parameter representation mapper
 *
 * @author Open group
 * @since 1.0.0
 */
@Component
public class EquipementRepresentationMapper  {
	@Autowired 
	private LocationRepresentationMapper locationRepresentationMapper;
  
    public DomainEquipement toOneDomain(EquipementRepresentation representation) throws DomainException {
    	
		return new DomainEquipement(representation.getStationNameEquipement(),
    			representation.getSerialNumberEquipement(),
    			representation.getMarkEquipement(),
    			representation.getModelEquipement(),
    			representation.getAttributionDateEquipement(),
    			representation.getReturnDateEquipement(),
    			representation.getPurchaseDateEquipement(),
    			representation.getExpectedDateEquipement(),
    	    	representation.getCommentsEquipement(),
    	    	representation.getEquipementType(),
    	    	representation.getStateType(),
    	    	locationRepresentationMapper.toOneDomain(representation.getLocationRepresentation()),
    			representation.getId()
    			);
    }

  
    public EquipementRepresentation toOneRepresentation(DomainEquipement domain){
    	//System.out.println("JE SUIS DANS toOneRepresentation :");
        EquipementRepresentation equipementRepresentation = new EquipementRepresentation();
        equipementRepresentation.setId(domain.getId());
        equipementRepresentation.setSerialNumberEquipement(domain.getSerialNumberEquipement());
        equipementRepresentation.setModelEquipement(domain.getModelEquipement().toString());
        equipementRepresentation.setMarkEquipement(domain.getMarkEquipement());
        equipementRepresentation.setStationNameEquipement(domain.getStationNameEquipement());
        equipementRepresentation.setPurchaseDateEquipement(domain.getPurchaseDateEquipement());
        equipementRepresentation.setReturnDateEquipement(domain.getReturnDateEquipement());
        equipementRepresentation.setAttributionDateEquipement(domain.getAttributionDateEquipement());
        equipementRepresentation.setExpectedDateEquipement(domain.getExpectedDateEquipement());
        equipementRepresentation.setCommentsEquipement(domain.getCommentsEquipement());
        equipementRepresentation.setEquipementType(domain.getEquipementType());
        equipementRepresentation.setStateType(domain.getStateType());
        equipementRepresentation.setLocationRepresenation(locationRepresentationMapper.toOneRepresentation(domain.getDomainLocation()));
        return equipementRepresentation;
    }

	
	public List<DomainEquipement> toDomains(Collection<EquipementRepresentation> equipementRepresentations) throws DomainException{
        List<DomainEquipement> domains = new ArrayList<>();
        if(equipementRepresentations != null){
            for(EquipementRepresentation rep : equipementRepresentations){
                domains.add(toOneDomain(rep));
            }
        }
        return domains;
    }

    
	
	
    public List<EquipementRepresentation> toRepresentations(Collection<DomainEquipement> domains) {
        List<EquipementRepresentation> equipementRepresentations = new ArrayList<>();
        if(domains != null){
            for(DomainEquipement domain : domains){
            	equipementRepresentations.add(toOneRepresentation(domain));
            }
        }
        return equipementRepresentations ;
    }


}
