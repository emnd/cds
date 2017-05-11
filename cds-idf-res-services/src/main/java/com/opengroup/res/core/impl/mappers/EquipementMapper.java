package com.opengroup.res.core.impl.mappers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.opengroup.res.core.domain.DomainCollaborator;
import com.opengroup.res.core.domain.DomainEquipement;
import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.core.domain.DomainLocation;
import com.opengroup.res.core.domain.DomainEquipement.EquipementType;
import com.opengroup.res.core.domain.DomainEquipement.StateType;
//import com.opengroup.res.jpa.entities.Authorisation;
import com.opengroup.res.jpa.entities.Equipement;
//import com.opengroup.res.jpa.entities.Location;
import com.opengroup.res.util.AbstractDomainMapper;
/**
 * A parameter mapper
 */
@Component
public class EquipementMapper extends AbstractDomainMapper<DomainEquipement, Equipement> {

	@Autowired
	private LocationMapper locationMapper;

	@Autowired
	private CollaboratorMapper collaboratorMapper;

	public DomainEquipement toOneDomain(Equipement equipement) throws DomainException {

		if (equipement == null) {
			return null;
		}

		Long id = equipement.getId();
		String stationName = equipement.getStationName();
		String serialNumber = equipement.getSerialNumber();
		String mark = equipement.getMark();
		String model = equipement.getModel();
		Date attributeDate = equipement.getAttributionDate();
		Date returnDate = equipement.getReturnDate();
		Date purchaseDate = equipement.getPurchaseDate();
		Date expectedDate = equipement.getExpectedDate();
		String comments = equipement.getComments();
		EquipementType equipementType = DomainEquipement.EquipementType.valueOf(equipement.getEquipmentType().toString());
		StateType stateType = DomainEquipement.StateType.valueOf(equipement.getStateType());
		DomainLocation domainLocation = locationMapper.toOneDomain(equipement.getLocation());
		DomainCollaborator domainCollaborator = (equipement.getCollaborator()!=null) ? collaboratorMapper.toOneDomain(equipement.getCollaborator()): null;
		//DomainCollaborator domainCollaborator = collaboratorMapper.toOneDomain(equipement.getCollaborator());
		DomainEquipement domainEquipement = new DomainEquipement(stationName,serialNumber,mark,model,attributeDate,returnDate,purchaseDate, expectedDate,comments, equipementType,stateType,domainLocation,domainCollaborator,id);


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
		DomainCollaborator domainCollaborator = (domainEquipement.getDomainCollaborator() != null) ? domainEquipement.getDomainCollaborator() : null;
		equipement.setCollaborator(collaboratorMapper.toOneEntity(domainCollaborator));

		return equipement;
	}


	public List<Equipement> convertDomainListToEntityList(List<DomainEquipement> listDomain) {

		if (listDomain == null) {

			return null;
		}

		List<Equipement> listEntity = new ArrayList<Equipement>();

		for (DomainEquipement domainEquipement : listDomain) {

			listEntity.add(toOneEntity(domainEquipement));
		}

		return listEntity;
	}


	public List<DomainEquipement> convertEntityListToDomainList(List<Equipement> listEntity) throws DomainException {

		if (listEntity == null) {

			return null;
		}

		List<DomainEquipement> listDomain = new ArrayList<DomainEquipement>();

		for (Equipement equipement : listEntity) {

			listDomain.add(toOneDomain(equipement));
		}

		return listDomain;
	}


	//mapper wth out Collaborator

	public DomainEquipement toOneDomainWithoutCollab(Equipement equipement) throws DomainException {

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
		//System.out.println("Mon domaine collaborator à partir entity dans toOneDomain:"+domainEquipement.getDomainCollaborator().toString());
		return domainEquipement;

	}



	public Equipement toOneEntityWithoutCollab(DomainEquipement domainEquipement) {
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



}
