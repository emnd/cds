package com.opengroup.res.core.domain;

import java.io.Serializable;
import java.util.Date;

import com.opengroup.res.enums.EquipementType;
import com.opengroup.res.enums.StateType;





/**
 * @author OAI15274
 *date début:22/12/2016
 */

public class DomainEquipement implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private Long idEquipement;
	private String stationNameEquipement;
	private String serialNumberEquipement;
	private String markEquipement;
	private String modelEquipement;
	private Date attributionDateEquipement;
	private Date returnDateEquipement;
	private Date purchaseDateEquipement;
	private Date expectedDateEquipement;
	private String commentsEquipement;

	private EquipementType equipementTypeEquipement;
	private StateType stateTypeEquipement;

	private DomainLocation domainLocation;
	
	/*public DomainEquipement() {
		super();
		// TODO Auto-generated constructor stub
	}*/

	  public DomainEquipement() {
			super();
		}

	  protected DomainEquipement( String stationNameEquipement, String serialNumberEquipement, String markEquipement, String modelEquipement, Date attributionDateEquipement, Date returnDateEquipement, Date purchaseDateEquipement, Date expectedDateEquipement, String commentsEquipement, EquipementType equipementTypeEquipement, StateType stateTypeEquipement) throws DomainException {
		
		  
				this.stationNameEquipement = stationNameEquipement;
		
				this.serialNumberEquipement = serialNumberEquipement;
			
				this.markEquipement = markEquipement;
		
				this.modelEquipement = modelEquipement;
		
				this.attributionDateEquipement = attributionDateEquipement;
			
				this.returnDateEquipement = returnDateEquipement;
		
				this.purchaseDateEquipement = purchaseDateEquipement;
			
				this.expectedDateEquipement = expectedDateEquipement;
		
				this.commentsEquipement = commentsEquipement;
		
				this.equipementTypeEquipement = equipementTypeEquipement;
		
				this.stateTypeEquipement = stateTypeEquipement;

	    }
	  
	

	public static DomainEquipement newCreatedStateInstance(String stationNameEquipement, String serialNumberEquipement, String markEquipement, String modelEquipement, Date attributionDateEquipement, Date returnDateEquipement, Date purchaseDateEquipement, Date expectedDateEquipement, String commentsEquipement, EquipementType equipementTypeEquipement, StateType stateTypeEquipement) throws DomainException {
	        return new DomainEquipement(stationNameEquipement,serialNumberEquipement,markEquipement, modelEquipement, attributionDateEquipement, returnDateEquipement, purchaseDateEquipement, expectedDateEquipement, commentsEquipement,  equipementTypeEquipement,  stateTypeEquipement );
	    }

	    public static DomainEquipement newModifiedStateInstance(String stationNameEquipement, String serialNumberEquipement, String markEquipement, String modelEquipement, Date attributionDateEquipement, Date returnDateEquipement, Date purchaseDateEquipement, Date expectedDateEquipement, String commentsEquipement, EquipementType equipementTypeEquipement, StateType stateTypeEquipement)throws DomainException {
	        return new DomainEquipement( stationNameEquipement,serialNumberEquipement,markEquipement, modelEquipement, attributionDateEquipement, returnDateEquipement, purchaseDateEquipement, expectedDateEquipement, commentsEquipement,  equipementTypeEquipement,  stateTypeEquipement );
	    }

	    public static DomainEquipement newSuppressedStateInstance(String stationNameEquipement, String serialNumberEquipement, String markEquipement, String modelEquipement, Date attributionDateEquipement, Date returnDateEquipement, Date purchaseDateEquipement, Date expectedDateEquipement, String commentsEquipement, EquipementType equipementTypeEquipement, StateType stateTypeEquipement) throws DomainException {
	        return new DomainEquipement( stationNameEquipement,serialNumberEquipement,markEquipement, modelEquipement, attributionDateEquipement, returnDateEquipement, purchaseDateEquipement, expectedDateEquipement, commentsEquipement,  equipementTypeEquipement,  stateTypeEquipement );
	    }

	    public static DomainEquipement newValidatedStateInstance(String stationNameEquipement, String serialNumberEquipement, String markEquipement, String modelEquipement, Date attributionDateEquipement, Date returnDateEquipement, Date purchaseDateEquipement, Date expectedDateEquipement, String commentsEquipement, EquipementType equipementTypeEquipement, StateType stateTypeEquipement) throws DomainException {
	        return new DomainEquipement( stationNameEquipement,serialNumberEquipement,markEquipement, modelEquipement, attributionDateEquipement, returnDateEquipement, purchaseDateEquipement, expectedDateEquipement, commentsEquipement,  equipementTypeEquipement,  stateTypeEquipement );
	    }

	    
	
	
	
	public Long getIdEquipement() {
		return idEquipement;
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

	public EquipementType getEquipementTypeEquipement() {
		return equipementTypeEquipement;
	}

	public StateType getStateTypeEquipement() {
		return stateTypeEquipement;
	}	
	

	
	public void setIdEquipement(Long idEquipement) {
		this.idEquipement = idEquipement;
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



	public void setEquipementTypeEquipement(EquipementType equipementTypeEquipement) {
		this.equipementTypeEquipement = equipementTypeEquipement;
	}



	public void setStateTypeEquipement(StateType stateTypeEquipement) {
		this.stateTypeEquipement = stateTypeEquipement;
	}



	public void setDomainLocation(DomainLocation domainLocation) {
		this.domainLocation = domainLocation;
	}



	public DomainLocation getdomainLocation() {
		return domainLocation;
	}

	public void setdomainLocation(DomainLocation domainLocation) {
		this.domainLocation = domainLocation;
	}

	@Override
	public String toString() {
		return "DomainEquipement [idEquipement=" + idEquipement + ", stationNameEquipement=" + stationNameEquipement
				+ ", serialNumberEquipement=" + serialNumberEquipement + ", markEquipement=" + markEquipement
				+ ", modelEquipement=" + modelEquipement + ", attributionDateEquipement=" + attributionDateEquipement
				+ ", returnDateEquipement=" + returnDateEquipement + ", purchaseDateEquipement="
				+ purchaseDateEquipement + ", expectedDateEquipement=" + expectedDateEquipement
				+ ", commentsEquipement=" + commentsEquipement + ", equipementTypeEquipement="
				+ equipementTypeEquipement + ", stateTypeEquipement=" + stateTypeEquipement +",domainLocation "+domainLocation.toString()+ "]";
	}
	
}
