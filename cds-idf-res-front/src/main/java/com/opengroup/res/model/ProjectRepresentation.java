package com.opengroup.res.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opengroup.res.util.RepresentationBean;

/**
 * Parameter representation
 *
 * @author Open group
 * @since 1.0.0
 */
public class ProjectRepresentation implements RepresentationBean {

	private Long id;
	private String projectName;
	private Date periodStart;
	private Date periodEnd;
	private List<AuthorisationRepresentation> authorisationRepresentationList = new ArrayList<AuthorisationRepresentation>();
	//private List<CustomerProjectRepresentation> customerProjectRepresentationList = new ArrayList<CustomerProjectRepresentation>();
	
	// getters et setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Date getPeriodStart() {
		return periodStart;
	}
	public void setPeriodStart(Date periodStart) {
		this.periodStart = periodStart;
	}
	public Date getPeriodEnd() {
		return periodEnd;
	}
	public void setPeriodEnd(Date periodEnd) {
		this.periodEnd = periodEnd;
	}
	public List<AuthorisationRepresentation> getAuthorisationRepresentationList() {
		return authorisationRepresentationList;
	}
	public void setAuthorisationRepresentationList(List<AuthorisationRepresentation> authorisationRepresentationList) {
		this.authorisationRepresentationList = authorisationRepresentationList;
	}
	
	
}
