package com.opengroup.res.mappers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.opengroup.res.core.domain.DomainAutorisation;
import com.opengroup.res.core.domain.DomainEquipement;
import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.core.domain.DomainLocation;
import com.opengroup.res.model.AuthorisationRepresentation;
import com.opengroup.res.model.EquipementRepresentation;
import com.opengroup.res.model.LocationRepresentation;
import com.opengroup.res.util.mappers.AbstractFrontMapper;
/**
 * Parameter representation mapper
 *
 * @author Open group
 * @since 1.0.0
 */
@Component
public class LocationRepresentationMapper extends AbstractFrontMapper<DomainLocation, LocationRepresentation> {

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


	public List<DomainLocation> convertListRepresentationToListDomainList(List<LocationRepresentation> representationList) throws DomainException
	{
		if (representationList == null) {

			return null;
		}

		List<DomainLocation> listDomain = new ArrayList<DomainLocation>();

		for (LocationRepresentation locationRepresentation : representationList) {

			listDomain.add(toOneDomain(locationRepresentation));
		}

		return listDomain;
	}

	public List<LocationRepresentation> convertListDomainListToListRepresentation(List<DomainLocation> domainList) throws DomainException
	{
		if (domainList == null) {

			return null;
		}

		List<LocationRepresentation> listRepresentation = new ArrayList<LocationRepresentation>();

		for (DomainLocation domainLocation : domainList) {

			listRepresentation.add(toOneRepresentation(domainLocation));
		}

		return listRepresentation;
	}


}
