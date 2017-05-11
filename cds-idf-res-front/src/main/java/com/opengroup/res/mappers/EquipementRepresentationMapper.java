package com.opengroup.res.mappers;

import java.util.ArrayList;
//import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import com.opengroup.res.core.domain.DomainAutorisation;
import com.opengroup.res.core.domain.DomainCollaborator;
import com.opengroup.res.core.domain.DomainEquipement;
import com.opengroup.res.core.domain.DomainException;
//import com.opengroup.res.model.AuthorisationRepresentation;
import com.opengroup.res.model.EquipementRepresentation;
import com.opengroup.res.util.mappers.AbstractFrontMapper;
/**
 * Parameter representation mapper
 *
 * @author Open group
 * @since 1.0.0
 */
@Component
public class EquipementRepresentationMapper extends AbstractFrontMapper<DomainEquipement, EquipementRepresentation> {
	@Autowired
	private LocationRepresentationMapper locationRepresentationMapper;

	@Autowired
	private CollaboratorRepresentationMapper collaboratorRepresentationMapper;

	public DomainEquipement toOneDomain(EquipementRepresentation representation) throws DomainException {

		DomainCollaborator domainCollaborator = representation.getCollaboratorRepresentation() != null ? collaboratorRepresentationMapper.toOneDomain(representation.getCollaboratorRepresentation()) : null;
		System.out.println("je suis le collaboraror "+domainCollaborator.toString());
		if( domainCollaborator != null){
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
					domainCollaborator,

					representation.getId()
			);
		}
		else{
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
	}


	public EquipementRepresentation toOneRepresentation(DomainEquipement domain){
		//System.out.println("JE SUIS DANS toOneRepresentation :");
		EquipementRepresentation equipementRepresentation = new EquipementRepresentation();
		equipementRepresentation.setId(domain.getId());
		equipementRepresentation.setSerialNumberEquipement(domain.getSerialNumberEquipement());
		equipementRepresentation.setModelEquipement(domain.getModelEquipement());
		equipementRepresentation.setMarkEquipement(domain.getMarkEquipement());
		equipementRepresentation.setStationNameEquipement(domain.getStationNameEquipement());
		equipementRepresentation.setPurchaseDateEquipement(domain.getPurchaseDateEquipement());
		equipementRepresentation.setReturnDateEquipement(domain.getReturnDateEquipement());
		equipementRepresentation.setAttributionDateEquipement(domain.getAttributionDateEquipement());
		equipementRepresentation.setExpectedDateEquipement(domain.getExpectedDateEquipement());
		equipementRepresentation.setCommentsEquipement(domain.getCommentsEquipement());
		equipementRepresentation.setEquipementType(domain.getEquipementType());
		equipementRepresentation.setStateType(domain.getStateType());
		equipementRepresentation.setLocationRepresentation(locationRepresentationMapper.toOneRepresentation(domain.getDomainLocation()));
		DomainCollaborator domainCollaborator = (domain.getDomainCollaborator() != null) ? domain.getDomainCollaborator() : null;
		if(domainCollaborator != null){
			equipementRepresentation.setCollaboratorRepresentation(collaboratorRepresentationMapper.toOneRepresentation(domainCollaborator));
		}

		return equipementRepresentation;
	}



	public List<DomainEquipement> convertListRepresentationToListDomainList(List<EquipementRepresentation> representationList) throws DomainException
	{
		//System.out.println("Je suis dans la liste representation pour domain");
		if (representationList == null) {

			return null;
		}

		List<DomainEquipement> listDomain = new ArrayList<DomainEquipement>();

		for (EquipementRepresentation equipementRepresentation : representationList) {

			listDomain.add(toOneDomain(equipementRepresentation));
		}

		return listDomain;
	}

	public List<EquipementRepresentation> convertListDomainListToListRepresentation(List<DomainEquipement> domainList) throws DomainException
	{
		//System.out.println("Je suis dans la liste Domain pour representation");
		if (domainList == null) {

			return null;
		}

		List<EquipementRepresentation> listRepresentation = new ArrayList<EquipementRepresentation>();

		for (DomainEquipement domainEquipement : domainList) {

			listRepresentation.add(toOneRepresentation(domainEquipement));
		}

		return listRepresentation;
	}

}
