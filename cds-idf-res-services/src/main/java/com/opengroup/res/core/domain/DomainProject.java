package com.opengroup.res.core.domain;
import com.opengroup.res.core.domain.DomainAutorisation;
//import com.opengroup.res.core.domain.DomainCustomerProject;
import com.opengroup.res.core.domain.DomainException;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import java.util.List;
//import java.util.Array;

/**
 * Project model Class
 *
 * @author Open group
 * @since 1.0.0
 */
public class DomainProject extends DomainBeanTrackable implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private Date periodStart;
    private Date periodEnd;
    private String projectName;
    //private List<DomainCustomerProject> domainCustomerProjectList = new ArrayList<DomainCustomerProject>();
    private List<DomainAutorisation> domainAutorisationList = new ArrayList<DomainAutorisation>();


    public DomainProject(String projectName, Date periodStart, Date periodEnd, Long id ) throws DomainException {

        if (StringUtils.isEmpty(projectName)) {
            constraintsErrors.add("A projectName must be provided");
        }

       /* if (periodStart == null) {
            constraintsErrors.add("A periodStart must be provided");
        }

        if (periodEnd == null) {
            constraintsErrors.add("A periodEnd must be provided");
        }
        */

        checkIfValid();
       this.projectName = projectName;
       this.periodStart = periodStart;
        this.periodEnd = periodEnd;
        this.id = id;
    }


    public static DomainProject newInstance(String projectName,Date periodStart, Date periodEnd,Long id) throws DomainException {
        return new DomainProject(projectName, periodStart, periodEnd,id);
    }

    public static DomainProject updateInstance(String projectName,Date periodStart, Date periodEnd, Long id) throws DomainException {
        return new DomainProject(projectName, periodStart, periodEnd,id);
    }

    public static DomainProject deleteInstance(String projectName,Date periodStart, Date periodEnd, Long id) throws DomainException {
        return new DomainProject(projectName, periodStart, periodEnd,id);
    }

     /*
    * GETTERS
    * */
     public Long getId() { return id; }
    public Date getPeriodStart() {return periodStart;}
    public Date getPeriodEnd() {return periodEnd;}
    public String getProjectName() {
        return projectName;
    }
    //public List<DomainCustomerProject> getDomainCustomerProjectList() { return domainCustomerProjectList;}
    public List<DomainAutorisation> getDomainAutorisationList() { return domainAutorisationList;}

    /*
     *SETTERS
     */
    public void setId( Long id) { this.id = id; }
    public void setPeriodStart(Date periodStart) { this.periodStart = periodStart; }
    public void setPeriodEnd(Date periodEnd) {this.periodEnd = periodEnd;}
    public void setProjectName(String projectName) {        this.projectName = projectName;    }
    //public void  setDomainCustomerProjectList(List<DomainCustomerProject> domainCustomerProjectList) { this.domainCustomerProjectList = domainCustomerProjectList;}
    public void  setDomainAutorisationList(List<DomainAutorisation> domainAutorisationList) { this.domainAutorisationList = domainAutorisationList;}


	@Override
	public String toString() {
		return "DomainProject [id=" + id + ", periodStart=" + periodStart + ", periodEnd=" + periodEnd
				+ ", projectName=" + projectName + "]";
	}


    
}
