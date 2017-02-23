package com.opengroup.res.jpa.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.springframework.format.annotation.DateTimeFormat;

import com.opengroup.res.util.EntityBean;



/**
 * @author Open groupe croissant
 *
 */

@Entity
@Table(name="project", uniqueConstraints={ @UniqueConstraint(columnNames={"nameProject"})})
@NamedQuery(name = "Project.findAll", query = "SELECT p FROM Project p")
public class Project implements Serializable, EntityBean{
	private static final long serialVersionUID = 1L;
	private Long idProject;
	private String nameProject;
	private Date periodStart;
	private Date periodEnd;
	private int version;
	private List<Authorisation> authorisations = new ArrayList<Authorisation>();
	//private List<CustomerProject> customerProject = new ArrayList<CustomerProject>();
	
	SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd/MM/yyyy");
	
	
	public Project() {
		
	}

	public Project( Long id,String nameProject, Date periodStart, Date periodEnd ) {
		super();
		this.idProject = id;
		this.nameProject = nameProject.toUpperCase();
		this.periodStart = periodStart;
		this.periodEnd = periodEnd;
	}
	


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getIdProject() {
		return idProject;
	}


	public void setIdProject(Long id) {
		this.idProject = id;
	}
	
	@Column(name="nameProject")
	public String getNameProject() {
		return nameProject;
	}


	public void setNameProject(String nameProject) {
		if (!nameProject.isEmpty()) this.nameProject = nameProject.toUpperCase();
	}

	@Column(name="periodStart")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getPeriodStart() {
		return periodStart;
	}


	public void setPeriodStart(Date periodStart) {
		this.periodStart = periodStart;
	}
 
	@Column(name="periodEnd")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getPeriodEnd() {
		return periodEnd;
	}


	public void setPeriodEnd(Date periodEnd) {
		this.periodEnd = periodEnd;
	}
	

	@Version
	@Column(name="Version")
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	

	@OneToMany(mappedBy = "project",cascade=CascadeType.ALL, orphanRemoval=true) // cascade=CascadeType.ALL, orphanRemoval=true pour la suppression en cascade (le 07 FEV 2017))
	public List<Authorisation> getAuthorisations() {
		return authorisations;
	}

	public void setAuthorisations(List<Authorisation> authorisations) {
		this.authorisations = authorisations;
	}


//	@OneToMany(mappedBy = "project")
//	public List<CustomerProject> getCustomerProject() {
//		return customerProject;
//	}
//
//	public void setCustomerProject(List<CustomerProject> customerProject) {
//		this.customerProject = customerProject;
//	}

	


}
