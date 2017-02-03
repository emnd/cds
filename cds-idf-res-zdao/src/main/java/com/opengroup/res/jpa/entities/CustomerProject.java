/**
 * 
 */
package com.opengroup.res.jpa.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;



/**
 * @author SGU15268
 *
 */
@Entity
@Table(name="customer_project")

public class CustomerProject implements  Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Customer customer;
	private Project project;
	private int version;
	
	public CustomerProject() {
		
		
	}

	public CustomerProject(Customer customer, Project project) {
	
		this.customer=customer;
		this.project=project;
		
		
	}
	
	/**
	 * @return the id
	 */
	@Id @GeneratedValue
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}


	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_customer")
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	

	@ManyToOne
	@JoinColumn(name="id_project")
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	
	@Version
	@Column(name="Version")
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CustomerProject [id=" + id + ", customer=" + customer + ", project=" + project + ", version=" + version
				+ "]";
	}

	
}
