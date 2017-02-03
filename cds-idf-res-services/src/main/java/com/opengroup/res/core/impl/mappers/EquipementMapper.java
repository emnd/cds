package com.opengroup.res.core.impl.mappers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.opengroup.res.core.domain.DomainEquipement;
import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.core.domain.DomainParameter;
import com.opengroup.res.jpa.entities.Equipement;
import com.opengroup.res.jpa.entities.Parameter;
import com.opengroup.res.util.AbstractDomainMapper;

/**
 * A parameter mapper
 */
@Component
public class EquipementMapper {
    
	public DomainEquipement toOneDomain(Equipement equipement) throws DomainException {
        
    	if(equipement == null){
			return null;
		}
		
    	DomainEquipement domainEquipement= new DomainEquipement();
   	 domainEquipement.setIdEquipement(equipement.getId());
    	 domainEquipement.setStationNameEquipement(equipement.getStationName());
 		domainEquipement.setSerialNumberEquipement(equipement.getSerialNumber());
 		domainEquipement.setMarkEquipement(equipement.getMark());
 		domainEquipement.setModelEquipement(equipement.getModel());
 		domainEquipement.setAttributionDateEquipement(equipement.getAttributionDate());
 		domainEquipement.setReturnDateEquipement(equipement.getReturnDate());
 		domainEquipement.setPurchaseDateEquipement(equipement.getPurchaseDate());
 		domainEquipement.setExpectedDateEquipement(equipement.getExpectedDate());
 		domainEquipement.setCommentsEquipement(equipement.getComments());

 		domainEquipement.setEquipementTypeEquipement(equipement.getEquipmentType());
 		domainEquipement.setStateTypeEquipement(equipement.getStateType());
 		
 	
    	
 		return  domainEquipement;
    	
    }

  
    public Equipement toOneEntity(DomainEquipement domainEquipement) {
        Date now = new Date();
        
        Equipement equipement = new Equipement();
        
        		
        equipement.setId(domainEquipement.getIdEquipement());
            
        equipement.setStationName(domainEquipement.getStationNameEquipement());
        equipement.setSerialNumber(domainEquipement.getSerialNumberEquipement());
        equipement.setMark(domainEquipement.getMarkEquipement());
        equipement.setModel(domainEquipement.getModelEquipement());
        equipement.setAttributionDate(domainEquipement.getAttributionDateEquipement());
        equipement.setReturnDate(domainEquipement.getReturnDateEquipement());
        equipement.setPurchaseDate(domainEquipement.getPurchaseDateEquipement());
        equipement.setExpectedDate(domainEquipement.getExpectedDateEquipement());
        equipement.setComments(domainEquipement.getCommentsEquipement());
		
        return equipement;
    }


	
    
	 public List<DomainEquipement> toDomains(Iterable<Equipement> iterable) throws DomainException{
	        List<DomainEquipement> domains = new ArrayList<>();
	        if(iterable != null){
	            for(Equipement bean : iterable){
	                domains.add(toOneDomain(bean));
	            }
	        }
	        return domains;
	    }
    
	 
    
    
}

