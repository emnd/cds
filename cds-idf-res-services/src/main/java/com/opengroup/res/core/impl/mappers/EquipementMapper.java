package com.opengroup.res.core.impl.mappers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opengroup.res.core.domain.DomainEquipement;
import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.core.domain.DomainLocation;
import com.opengroup.res.core.domain.DomainEquipement.EquipementType;
import com.opengroup.res.core.domain.DomainEquipement.StateType;
import com.opengroup.res.jpa.entities.Equipement;
/**
 * A parameter mapper
 */
@Component
public class EquipementMapper {

	@Autowired
	private LocationMapper locationMapper;

	public DomainEquipement toOneDomain(Equipement equipement) throws DomainException {

		if (equipement == null) {
			return null;
		}
		

		DomainEquipement domainEquipement = new DomainEquipement(
				equipement.getStationName(),
				equipement.getSerialNumber(),
				equipement.getMark(),
				equipement.getModel(),
				equipement.getAttributionDate(),
				equipement.getReturnDate(),
				equipement.getPurchaseDate(), 
				equipement.getExpectedDate(),
				equipement.getComments(), 
				DomainEquipement.EquipementType.valueOf(equipement.getEquipmentType().toString()),
				DomainEquipement.StateType.valueOf(equipement.getStateType()),
			locationMapper.toOneDomain(equipement.getLocation()),
				equipement.getId()
				);
		
		return domainEquipement;

	}
	

	
	public Equipement toOneEntity(DomainEquipement domainEquipement) {
		//Date now = new Date();

		Equipement equipement = new Equipement();

		
        equipement.setId(domainEquipement.getId());
		equipement.setStationName(domainEquipement.getStationNameEquipement());
		equipement.setSerialNumber(domainEquipement.getSerialNumberEquipement());
		equipement.setMark(domainEquipement.getMarkEquipement());
		equipement.setModel(domainEquipement.getModelEquipement());
		equipement.setAttributionDate(domainEquipement.getAttributionDateEquipement());
		equipement.setReturnDate(domainEquipement.getReturnDateEquipement());
		equipement.setPurchaseDate(domainEquipement.getPurchaseDateEquipement());
		equipement.setExpectedDate(domainEquipement.getExpectedDateEquipement());
		equipement.setComments(domainEquipement.getCommentsEquipement());
		equipement.setEquipmentType(domainEquipement.getEquipementType().toString());
		equipement.setStateType(domainEquipement.getStateType().toString());
		equipement.setLocation(locationMapper.toOneEntity(domainEquipement.getDomainLocation()));

		return equipement;
	}

	public List<DomainEquipement> toDomains(Iterable<Equipement> iterable) throws DomainException {
		List<DomainEquipement> domains = new ArrayList<>();
		if (iterable != null) {
			for (Equipement bean : iterable) {
				domains.add(toOneDomain(bean));
			}
		}
		return domains;
	}

}
