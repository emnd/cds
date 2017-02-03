package com.opengroup.res.core.domain;

import java.io.Serializable;

/**
 * @author KAI15273
 *
 */

public class DomainLocation implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long idLocation;
	private String nameLocation;
	private String blockLocation;
	private String placeLocation;

	
	/*
	 * Empty Constructor
	 */
	public DomainLocation() {
		super();
	}


	  protected DomainLocation(String nameLocation, String blockLocation, String placeLocation) throws DomainException {

			
			this.nameLocation = nameLocation;
			this.blockLocation = blockLocation;
			this.placeLocation = placeLocation;
		
	    }
	  
	  public static DomainLocation newCreatedStateInstance(String nameLocation, String blockLocation, String placeLocation) throws DomainException {
	        return new DomainLocation(nameLocation,  blockLocation, placeLocation);
	    }

	    public static DomainLocation newModifiedStateInstance(String nameLocation, String blockLocation, String placeLocation)throws DomainException {
	        return new DomainLocation(nameLocation,  blockLocation, placeLocation);
	    }

	    public static DomainLocation newSuppressedStateInstance(String nameLocation, String blockLocation, String placeLocation) throws DomainException {
	        return new DomainLocation(nameLocation,  blockLocation, placeLocation);
	    }

	    public static DomainLocation newValidatedStateInstance(String nameLocation, String blockLocation, String placeLocation) throws DomainException {
	        return new DomainLocation( nameLocation,  blockLocation, placeLocation);
	    }

	
	/*
	 * Getters and Setters
	 */
	
	public Long getIdLocation() {
		return idLocation;
	}

	public String getNameLocation() {
		return nameLocation;
	}

	public String getBlockLocation() {
		return blockLocation;
	}
	
    public String getPlaceLocation() {
		return placeLocation;
	}


	
	public void setIdLocation(Long idLocation) {
		this.idLocation = idLocation;
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
		return "DomainLocation [idLocation=" + idLocation + ", nameLocation=" + nameLocation + ", blockLocation="
				+ blockLocation + ", placeLocation=" + placeLocation + "]";
	}
}
