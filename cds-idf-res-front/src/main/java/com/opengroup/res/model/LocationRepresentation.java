package com.opengroup.res.model;

import java.util.ArrayList;
import java.util.List;

import com.opengroup.res.util.RepresentationBean;
/**
 * Parameter representation
 *
 * @author Open group
 * @since 1.0.0
 */
public class LocationRepresentation implements RepresentationBean {
	//private static final long serialVersionUID = 1L;
	private Long id;
	private String nameLocation;
	private String blockLocation;
	private String placeLocation;
	private List<EquipementRepresentation> equipementRepresentationList = new ArrayList<EquipementRepresentation>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNameLocation() {
		return nameLocation;
	}
	public void setNameLocation(String nameLocation) {
		this.nameLocation = nameLocation;
	}
	public String getBlockLocation() {
		return blockLocation;
	}
	public void setBlockLocation(String blockLocation) {
		this.blockLocation = blockLocation;
	}
	public String getPlaceLocation() {
		return placeLocation;
	}
	public void setPlaceLocation(String placeLocation) {
		this.placeLocation = placeLocation;
	}
	/*public static long getSerialversionuid() {
		return serialVersionUID;
	}*/


	public List<EquipementRepresentation> getEquipementRepresentationList() {
		return equipementRepresentationList;
	}

	public void setEquipementRepresentationList(List<EquipementRepresentation> equipementRepresentationList) {
		this.equipementRepresentationList = equipementRepresentationList;
	}




}
