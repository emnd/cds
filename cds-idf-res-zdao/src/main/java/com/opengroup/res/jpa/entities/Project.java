package com.opengroup.res.jpa.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import net.minidev.json.annotate.JsonIgnore;

/**
 * @author MMO15271
 *
 */

@Entity
@Table(name="project", uniqueConstraints={ @UniqueConstraint(columnNames={"nameProject"})})
@NamedQuery(name = "Project.findAll", query = "SELECT p FROM Project p")
public class Project implements Serializable, EntityBean{
	@Column(name = "project_state", nullable = false, length = 30)
	private String projectState;
	private static final long serialVersionUID = 1L;
	private AuditState auditState;
	private Long idProject;
	private String nameProject;
	private Date periodStart;
	private Date periodEnd;
	private int version;
	private List<Authorisation> authorisations = new ArrayList<Authorisation>();
	private List<CustomerProject> customerProject = new ArrayList<CustomerProject>();
	
	public Project(String nameProject) {
		// TODO Auto-generated constructor stub
		super();
		this.nameProject = nameProject;
	}
	
	public Project() {
		// TODO Auto-generated constructor stub
	}

	public Project( Long idProject,String nameProject, Date periodStart, Date periodEnd ) {
		super();
		this.idProject = idProject;
		this.nameProject = nameProject.toUpperCase();
		this.periodStart = periodStart;
		this.periodEnd = periodEnd;
	}
	


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getIdProject() {
		return idProject;
	}


	public void setIdProject(Long idProject) {
		this.idProject = idProject;
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
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	public Date getPeriodStart() {
		return periodStart;
	}


	public void setPeriodStart(Date periodStart) {
		this.periodStart = periodStart;
	}
 
	@Column(name="periodEnd")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy/MM/dd")
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

	
	@JsonIgnore
	@OneToMany(mappedBy = "project")
	public List<Authorisation> getAuthorisations() {
		return authorisations;
	}

	public void setAuthorisations(List<Authorisation> authorisations) {
		this.authorisations = authorisations;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "project")
	public List<CustomerProject> getCustomerProject() {
		return customerProject;
	}

	public void setCustomerProject(List<CustomerProject> customerProject) {
		this.customerProject = customerProject;
	}

//	@Override
//	public String toString() {
//		return "Project [idProject=" + idProject + ", nameProject=" + nameProject + ", periodStart=" + periodStart + ", periodEnd="
//				+ periodEnd + ", version=" + version + ", authorisations=" + authorisations + ", customerProject="
//				+ customerProject + "]";
//	}

	public AuditState getAuditState() {
		return auditState;
	}

	public void setAuditState(AuditState auditState) {
		this.auditState = auditState;
	}

	public String getProjectState() {
		return projectState;
	}

	public void setProjectState(String projectState) {
		this.projectState = projectState;
	}

	

	
	

}
