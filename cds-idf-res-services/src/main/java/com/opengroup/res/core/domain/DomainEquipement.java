package com.opengroup.res.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author OAI15274
 *date début:22/12/2016
 */

public class DomainEquipement extends DomainBeanTrackable implements Serializable {


	private static final long serialVersionUID = 1L;
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


	private DomainLocation domainLocation;

	private DomainCollaborator domainCollaborator;
	/*
	 private EquipementType equipmentType;
		private StateType stateType;*/

	public enum EquipementType{
		Laptop("Laptop"), Desktop("Desktop");
		private final String label;

		private EquipementType(String label) {
			this.label = label;

		}

		public String getLabel() {
			return label;
		}
	}

	public enum StateType{
		Available("Available"), Loaned("Loaned");
		private final String label;

		private StateType(String label) {
			this.label = label;

		}

		public String getLabel() {
			return label;
		}
	}
		    
		   
		
	/*public DomainEquipement() {
		super();
		// TODO Auto-generated constructor stub
	}*/

	public DomainEquipement() {
		super();
	}





	public DomainEquipement( String stationNameEquipement, String serialNumberEquipement, String markEquipement,
							 String modelEquipement, Date attributionDateEquipement, Date returnDateEquipement, Date purchaseDateEquipement,
							 Date expectedDateEquipement, String commentsEquipement, EquipementType equipementType,
							 StateType stateType,DomainLocation domainLocation,DomainCollaborator domainCollaborator ,Long id) throws DomainException {


		this.stationNameEquipement = stationNameEquipement;

		this.serialNumberEquipement = serialNumberEquipement;

		this.markEquipement = markEquipement;

		this.modelEquipement = modelEquipement;

		this.attributionDateEquipement = attributionDateEquipement;

		this.returnDateEquipement = returnDateEquipement;

		this.purchaseDateEquipement = purchaseDateEquipement;

		this.expectedDateEquipement = expectedDateEquipement;

		this.commentsEquipement = commentsEquipement;
		this.equipementType= equipementType;
		this.stateType = stateType;

		this.domainLocation = domainLocation;
		this.domainCollaborator = domainCollaborator;
		this.id=id;

	}

	public DomainEquipement( String stationNameEquipement, String serialNumberEquipement, String markEquipement,
							 String modelEquipement, Date attributionDateEquipement, Date returnDateEquipement, Date purchaseDateEquipement,
							 Date expectedDateEquipement, String commentsEquipement, EquipementType equipementType,
							 StateType stateType,DomainLocation domainLocation,Long id) throws DomainException {


		this.stationNameEquipement = stationNameEquipement;

		this.serialNumberEquipement = serialNumberEquipement;

		this.markEquipement = markEquipement;

		this.modelEquipement = modelEquipement;

		this.attributionDateEquipement = attributionDateEquipement;

		this.returnDateEquipement = returnDateEquipement;

		this.purchaseDateEquipement = purchaseDateEquipement;

		this.expectedDateEquipement = expectedDateEquipement;

		this.commentsEquipement = commentsEquipement;
		this.equipementType= equipementType;
		this.stateType = stateType;

		this.domainLocation = domainLocation;
		this.id=id;

	}

	public static DomainEquipement newCreatedStateInstance(String stationNameEquipement, String serialNumberEquipement,
														   String markEquipement, String modelEquipement, Date attributionDateEquipement, Date returnDateEquipement,
														   Date purchaseDateEquipement, Date expectedDateEquipement, String commentsEquipement,
														   EquipementType equipementType, StateType stateType,DomainLocation domainLocation, Long id) throws DomainException {
		return new DomainEquipement(stationNameEquipement,serialNumberEquipement,markEquipement, modelEquipement,
				attributionDateEquipement, returnDateEquipement, purchaseDateEquipement, expectedDateEquipement,
				commentsEquipement,  equipementType,  stateType, domainLocation,id);
	}

	public static DomainEquipement newModifiedStateInstance(String stationNameEquipement,
															String serialNumberEquipement, String markEquipement, String modelEquipement,
															Date attributionDateEquipement, Date returnDateEquipement, Date purchaseDateEquipement,
															Date expectedDateEquipement, String commentsEquipement, EquipementType equipementType,
															StateType stateType, DomainLocation domainLocation, Long id)throws DomainException {
		return new DomainEquipement(stationNameEquipement,serialNumberEquipement,markEquipement,
				modelEquipement, attributionDateEquipement, returnDateEquipement, purchaseDateEquipement,
				expectedDateEquipement, commentsEquipement,  equipementType,  stateType, domainLocation, id);
	}


	public static DomainEquipement newSuppressedStateInstance(String stationNameEquipement,
															  String serialNumberEquipement, String markEquipement, String modelEquipement,
															  Date attributionDateEquipement, Date returnDateEquipement, Date purchaseDateEquipement,
															  Date expectedDateEquipement, String commentsEquipement, EquipementType equipementType, StateType stateType,
															  DomainLocation domainLocation, Long id) throws DomainException {
		return new DomainEquipement(stationNameEquipement,serialNumberEquipement,markEquipement,
				modelEquipement, attributionDateEquipement, returnDateEquipement, purchaseDateEquipement,
				expectedDateEquipement, commentsEquipement,  equipementType,  stateType,
				domainLocation, id);
	}

	public static DomainEquipement newValidatedStateInstance(String stationNameEquipement,
															 String serialNumberEquipement, String markEquipement, String modelEquipement,
															 Date attributionDateEquipement, Date returnDateEquipement, Date purchaseDateEquipement,
															 Date expectedDateEquipement, String commentsEquipement,EquipementType equipementType, StateType stateType,
															 DomainLocation domainLocation, Long id) throws DomainException {
		return new DomainEquipement(stationNameEquipement,serialNumberEquipement,markEquipement,
				modelEquipement, attributionDateEquipement, returnDateEquipement, purchaseDateEquipement,
				expectedDateEquipement, commentsEquipement,  equipementType,  stateType,
				domainLocation, id);
	}





	public DomainEquipement(Long id) {
		this.id = id;
	}


	public static DomainEquipement newCreatedStateInstance(String stationNameEquipement, String serialNumberEquipement,
														   String markEquipement, String modelEquipement, Date attributionDateEquipement, Date returnDateEquipement,
														   Date purchaseDateEquipement, Date expectedDateEquipement, String commentsEquipement,
														   EquipementType equipementType, StateType stateType,DomainLocation domainLocation,DomainCollaborator domainCollaborator ,Long id) throws DomainException {
		return new DomainEquipement(stationNameEquipement,serialNumberEquipement,markEquipement, modelEquipement,
				attributionDateEquipement, returnDateEquipement, purchaseDateEquipement, expectedDateEquipement,
				commentsEquipement,  equipementType,  stateType, domainLocation,domainCollaborator,id);
	}

	public static DomainEquipement newModifiedStateInstance(String stationNameEquipement,
															String serialNumberEquipement, String markEquipement, String modelEquipement,
															Date attributionDateEquipement, Date returnDateEquipement, Date purchaseDateEquipement,
															Date expectedDateEquipement, String commentsEquipement, EquipementType equipementType,
															StateType stateType, DomainLocation domainLocation,DomainCollaborator domainCollaborator ,Long id)throws DomainException {
		return new DomainEquipement(stationNameEquipement,serialNumberEquipement,markEquipement,
				modelEquipement, attributionDateEquipement, returnDateEquipement, purchaseDateEquipement,
				expectedDateEquipement, commentsEquipement,  equipementType,  stateType, domainLocation,domainCollaborator, id);
	}

	public static DomainEquipement newSuppressedStateInstance(String stationNameEquipement,
															  String serialNumberEquipement, String markEquipement, String modelEquipement,
															  Date attributionDateEquipement, Date returnDateEquipement, Date purchaseDateEquipement,
															  Date expectedDateEquipement, String commentsEquipement, EquipementType equipementType, StateType stateType,
															  DomainLocation domainLocation,DomainCollaborator domainCollaborator, Long id) throws DomainException {
		return new DomainEquipement(stationNameEquipement,serialNumberEquipement,markEquipement,
				modelEquipement, attributionDateEquipement, returnDateEquipement, purchaseDateEquipement,
				expectedDateEquipement, commentsEquipement,  equipementType,  stateType,
				domainLocation,domainCollaborator,id );
	}

	public static DomainEquipement newValidatedStateInstance(String stationNameEquipement,
															 String serialNumberEquipement, String markEquipement, String modelEquipement,
															 Date attributionDateEquipement, Date returnDateEquipement, Date purchaseDateEquipement,
															 Date expectedDateEquipement, String commentsEquipement,EquipementType equipementType, StateType stateType,
															 DomainLocation domainLocation,DomainCollaborator domainCollaborator, Long id) throws DomainException {
		return new DomainEquipement(stationNameEquipement,serialNumberEquipement,markEquipement,
				modelEquipement, attributionDateEquipement, returnDateEquipement, purchaseDateEquipement,
				expectedDateEquipement, commentsEquipement,  equipementType,  stateType,
				domainLocation,domainCollaborator,id );
	}





	public Long getId() {
		return id;
	}


	public String getStationNameEquipement() {
		return stationNameEquipement;
	}


	public String getSerialNumberEquipement() {
		return serialNumberEquipement;
	}


	public String getMarkEquipement() {
		return markEquipement;
	}

	public String getModelEquipement() {
		return modelEquipement;
	}

	public Date getAttributionDateEquipement() {
		return attributionDateEquipement;
	}

	public Date getReturnDateEquipement() {
		return returnDateEquipement;
	}

	public Date getPurchaseDateEquipement() {
		return purchaseDateEquipement;
	}

	public Date getExpectedDateEquipement() {
		return expectedDateEquipement;
	}

	public String getCommentsEquipement() {
		return commentsEquipement;
	}





	public void setId(Long idEquipement) {
		this.id = idEquipement;
	}



	public void setStationNameEquipement(String stationNameEquipement) {
		this.stationNameEquipement = stationNameEquipement;
	}



	public void setSerialNumberEquipement(String serialNumberEquipement) {
		this.serialNumberEquipement = serialNumberEquipement;
	}



	public void setMarkEquipement(String markEquipement) {
		this.markEquipement = markEquipement;
	}



	public void setModelEquipement(String modelEquipement) {
		this.modelEquipement = modelEquipement;
	}



	public void setAttributionDateEquipement(Date attributionDateEquipement) {
		this.attributionDateEquipement = attributionDateEquipement;
	}



	public void setReturnDateEquipement(Date returnDateEquipement) {
		this.returnDateEquipement = returnDateEquipement;
	}



	public void setPurchaseDateEquipement(Date purchaseDateEquipement) {
		this.purchaseDateEquipement = purchaseDateEquipement;
	}



	public void setExpectedDateEquipement(Date expectedDateEquipement) {
		this.expectedDateEquipement = expectedDateEquipement;
	}



	public void setCommentsEquipement(String commentsEquipement) {
		this.commentsEquipement = commentsEquipement;
	}







	public EquipementType getEquipementType() {
		return equipementType;
	}





	public StateType getStateType() {
		return stateType;
	}





	public DomainLocation getDomainLocation() {
		return domainLocation;
	}





	public void setDomainLocation(DomainLocation domainLocation) {
		this.domainLocation = domainLocation;
	}





	public DomainCollaborator getDomainCollaborator() {
		return domainCollaborator;
	}





	public void setDomainCollaborator(DomainCollaborator domainCollaborator) {
		this.domainCollaborator = domainCollaborator;
	}





	@Override
	public String toString() {
		return "DomainEquipement [idEquipement=" + id + ", stationNameEquipement=" + stationNameEquipement
				+ ", serialNumberEquipement=" + serialNumberEquipement + ", markEquipement=" + markEquipement
				+ ", modelEquipement=" + modelEquipement + ", attributionDateEquipement=" + attributionDateEquipement
				+ ", returnDateEquipement=" + returnDateEquipement + ", purchaseDateEquipement="
				+ purchaseDateEquipement + ", expectedDateEquipement=" + expectedDateEquipement
				+ ", commentsEquipement=" + commentsEquipement + ", equipementTypeEquipement="
				+ equipementType + ", stateTypeEquipement=" + stateType +",domainLocation "+domainLocation.toString()+ ",domainCollaborator "+domainCollaborator.toString()+"]";
	}

}
