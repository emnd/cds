package com.opengroup.res.jpa.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.opengroup.res.util.EntityBean;


/**
 * Data-model class for a "Location", the location where the "equipement" and "collaborator" are present
 * 
 * @author KAI15273.
 */
@Entity
@Table(name="location")
@NamedQuery(name = "Location.findAll", query = "SELECT l FROM Location l")
public class Location implements Serializable, EntityBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private int version;
	private String name;
	private String block;
	private String place;
	
	private List<Equipement> equipments = new ArrayList<Equipement>();
	
	public Location() {
		super();
	}

	public Location(String name, String block, String place) {
	super();
	this.name = name;
	this.block = block;
	this.place = place;
}
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Version
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Column(name="openSpaceName")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="block")
	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	@Column(name="place")
	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	
	
	
	@OneToMany(mappedBy="location")
	public List<Equipement> getEquipments() {
		return equipments;
	}

	public void setEquipments(List<Equipement> equipments) {
		this.equipments = equipments;
	}	

}
