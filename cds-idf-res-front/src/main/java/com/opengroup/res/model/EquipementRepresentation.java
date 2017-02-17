package com.opengroup.res.model;

import java.util.Date;

import com.opengroup.res.core.domain.DomainEquipement.EquipementType;
import com.opengroup.res.core.domain.DomainEquipement.StateType;
//import com.opengroup.res.core.domain.DomainLocation;
import com.opengroup.res.util.RepresentationBean;

/**
 * Parameter representation
 *
 * @author Open group
 * @since 1.0.0
 */
public class EquipementRepresentation implements RepresentationBean {

	//private static final long serialVersionUID = 1L;
	private Long id;
	private String stationNameEquipement;
	private String serialNumberEquipement;
	private String markEquipement;
	private String modelEquipement;
	private Date attributionDateEquipement;
	private Date returnDateEquipement;
	private Date purchaseDateEquipement;
	private Date expectedDateEquipement;
	private String commentsEquipement;

	private EquipementType equipementType;
	private StateType stateType;
	
	private LocationRepresentation locationRepresentation;




	public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

	public String getStationNameEquipement() {
		return stationNameEquipement;
	}

	public void setStationNameEquipement(String stationNameEquipement) {
		this.stationNameEquipement = stationNameEquipement;
	}

	public String getSerialNumberEquipement() {
		return serialNumberEquipement;
	}

	public void setSerialNumberEquipement(String serialNumberEquipement) {
		this.serialNumberEquipement = serialNumberEquipement;
	}

	public String getMarkEquipement() {
		return markEquipement;
	}

	public void setMarkEquipement(String markEquipement) {
		this.markEquipement = markEquipement;
	}

	public String getModelEquipement() {
		return modelEquipement;
	}

	public void setModelEquipement(String modelEquipement) {
		this.modelEquipement = modelEquipement;
	}

	public Date getAttributionDateEquipement() {
		return attributionDateEquipement;
	}

	public void setAttributionDateEquipement(Date attributionDateEquipement) {
		this.attributionDateEquipement = attributionDateEquipement;
	}

	public Date getReturnDateEquipement() {
		return returnDateEquipement;
	}

	public void setReturnDateEquipement(Date returnDateEquipement) {
		this.returnDateEquipement = returnDateEquipement;
	}

	public Date getPurchaseDateEquipement() {
		return purchaseDateEquipement;
	}

	public void setPurchaseDateEquipement(Date purchaseDateEquipement) {
		this.purchaseDateEquipement = purchaseDateEquipement;
	}

	public Date getExpectedDateEquipement() {
		return expectedDateEquipement;
	}

	public void setExpectedDateEquipement(Date expectedDateEquipement) {
		this.expectedDateEquipement = expectedDateEquipement;
	}

	public String getCommentsEquipement() {
		return commentsEquipement;
	}

	public void setCommentsEquipement(String commentsEquipement) {
		this.commentsEquipement = commentsEquipement;
	}



	

	public EquipementType getEquipementType() {
		return equipementType;
	}

	public void setEquipementType(EquipementType equipementType) {
		this.equipementType = equipementType;
	}

	public StateType getStateType() {
		return stateType;
	}

	public void setStateType(StateType stateType) {
		this.stateType = stateType;
	}

	public LocationRepresentation getLocationRepresentation() {
		return locationRepresentation;
	}

	public void setLocationRepresenation(LocationRepresentation locationRepresentation) {
		this.locationRepresentation = locationRepresentation;
	}

	

	
	
}
