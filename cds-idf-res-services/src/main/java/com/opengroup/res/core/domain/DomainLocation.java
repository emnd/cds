package com.opengroup.res.core.domain;

import java.io.Serializable;
/**
 * @author KAI15273
 *
 */
public class DomainLocation extends DomainBeanTrackable implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String nameLocation;
	private String blockLocation;
	private String placeLocation;


	/*
	 * Empty Constructor
	 */
	public DomainLocation() {
		super();
	}


	public DomainLocation(String nameLocation, String blockLocation, String placeLocation, Long id) throws DomainException {


		this.nameLocation = nameLocation;
		this.blockLocation = blockLocation;
		this.placeLocation = placeLocation;
		this.id = id;

	}

	public static DomainLocation newCreatedStateInstance(String nameLocation, String blockLocation, String placeLocation,Long id) throws DomainException {
		return new DomainLocation(nameLocation,  blockLocation, placeLocation,id);
	}

	public static DomainLocation newModifiedStateInstance(String nameLocation, String blockLocation, String placeLocation,Long id)throws DomainException {
		return new DomainLocation(nameLocation,  blockLocation, placeLocation,id);
	}

	public static DomainLocation newSuppressedStateInstance(String nameLocation, String blockLocation, String placeLocation,Long id) throws DomainException {
		return new DomainLocation(nameLocation,  blockLocation, placeLocation,id);
	}

	public static DomainLocation newValidatedStateInstance(String nameLocation, String blockLocation, String placeLocation,Long id) throws DomainException {
		return new DomainLocation( nameLocation,  blockLocation, placeLocation,id);
	}

	
	/*
	 * Getters and Setters
	 */





	public String getNameLocation() {
		return nameLocation;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getBlockLocation() {
		return blockLocation;
	}

	public String getPlaceLocation() {
		return placeLocation;
	}





	public void setNameLocation(String nameLocation) {
		this.nameLocation = nameLocation;
	}


	public void setBlockLocation(String blockLocation) {
		this.blockLocation = blockLocation;
	}


	public void setPlaceLocation(String placeLocation) {
		this.placeLocation = placeLocation;
	}


	@Override
	public String toString() {
		return "DomainLocation [id=" + id + ", nameLocation=" + nameLocation + ", blockLocation="
				+ blockLocation + ", placeLocation=" + placeLocation + "]";
	}
}
