package com.opengroup.res.jpa.entities;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import com.opengroup.res.util.EntityBean;

/**
 * @author OAI15274
 *date début:22/12/2016
 */

@Entity
@Table(name = "equipement")

public class Equipement implements Serializable, EntityBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String stationName;
	private String serialNumber;
	private String mark;
	private String model;
	private Date attributionDate;
	private Date returnDate;
	private Date purchaseDate;
	private Date expectedDate;
	private String comments;
    private String equipmentType;
	private String stateType;

	
	private int version;

	private Location location;

	public Equipement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Equipement(String stationName, String serialNumber, String mark, String model, Date attributionDate,
			Date returnDate, Date purchaseDate, Date expectedDate, String comments, String equipmentType,
			String stateType, Location location) {
		super();
		this.stationName = stationName;
		this.serialNumber = serialNumber;
		this.mark = mark;
		this.model = model;
		this.attributionDate = attributionDate;
		this.returnDate = returnDate;
		this.purchaseDate = purchaseDate;
		this.expectedDate = expectedDate;
		this.comments = comments;
		this.equipmentType = equipmentType;
		this.stateType = stateType;
	
		this.location = location;
	}




	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Temporal(TemporalType.DATE)
	public Date getAttributionDate() {
		return attributionDate;
	}

	public void setAttributionDate(Date attributionDate) {
		this.attributionDate = attributionDate;
	}
	@Temporal(TemporalType.DATE)
	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	@Temporal(TemporalType.DATE)
	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	@Temporal(TemporalType.DATE)
	public Date getExpectedDate() {
		return expectedDate;
	}

	public void setExpectedDate(Date expectedDate) {
		this.expectedDate = expectedDate;
	}

	
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}


	
	


	public String getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	public String getStateType() {
		return stateType;
	}

	public void setStateType(String stateType) {
		this.stateType = stateType;
	}

	public int getVersion() {
		return version;
	}
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
		
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	
	@ManyToOne
	@JoinColumn(name = "equipement_location_id")
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Equipement [id=" + id + ", stationName=" + stationName + ", serialNumber=" + serialNumber + ", mark="
				+ mark + ", model=" + model + ", attributionDate=" + attributionDate + ", returnDate=" + returnDate
				+ ", purchaseDate=" + purchaseDate + ", expectedDate=" + expectedDate + ", comments=" + comments
				+ ", equipmentType=" + equipmentType + ", stateType=" + stateType +",location= "+location.toString()+ "]";
	}
	
}
