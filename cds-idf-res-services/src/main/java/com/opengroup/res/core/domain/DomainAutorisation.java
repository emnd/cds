package com.opengroup.res.core.domain;

//import com.opengroup.res.core.domain.DomainBean;
import com.opengroup.res.core.domain.DomainException;
//import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;
//import com.opengroup.res.jpa.entities.Request;
//import com.opengroup.res.jpa.entities.Collaborator;
import com.opengroup.res.core.domain.DomainCollaborator;
import com.opengroup.res.core.domain.DomainRequest;
import com.opengroup.res.core.domain.DomainProject;

/**
 * Autorisation model Class
 *
 * @author Open group
 * @since 1.0.0
 */
public class DomainAutorisation extends DomainBeanTrackable implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private DomainCollaborator domainCollaborator;
    private DomainRequest domainRequest;
    private Date periodStart;
    private Date periodEnd;
    private DomainProject domainProject;
    private boolean equipement;
    private String motive;
    private String status;


    public DomainAutorisation(DomainCollaborator domainCollaborator, DomainRequest domainRequest, Date periodStart, Date periodEnd, DomainProject domainProject, boolean equipement, String motive, String status, Long id ) throws DomainException {

        if (periodStart == null) {
            constraintsErrors.add("A periodStart must be provided");
        }

        if (periodEnd == null) {
            constraintsErrors.add("A periodEnd must be provided");
        }

        if(domainCollaborator == null){
            constraintsErrors.add("A Collaborator must be provided");
        }

        if(domainProject == null){
            constraintsErrors.add("A project must be provided");
        }

        if(domainRequest == null){
            constraintsErrors.add("A Request must be provided");
        }

        checkIfValid();
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
        this.domainCollaborator = domainCollaborator;
        this.domainProject = domainProject;
        this.domainRequest = domainRequest;
        this.id = id;
        this.equipement = equipement;
        this.status = status;
        this.motive = motive;
    }


    public static DomainAutorisation newInstance(DomainCollaborator domainCollaborator, DomainRequest domainRequest, Date periodStart, Date periodEnd, DomainProject domainProject, boolean equipement, String motive, String status,Long id) throws DomainException {
        return new DomainAutorisation(domainCollaborator, domainRequest, periodStart, periodEnd, domainProject, equipement, motive, status, id);
    }

    public static DomainAutorisation updateInstance(DomainCollaborator domainCollaborator, DomainRequest domainRequest, Date periodStart, Date periodEnd, DomainProject domainProject, boolean equipement, String motive, String status, Long id) throws DomainException {
        return new DomainAutorisation(domainCollaborator, domainRequest, periodStart, periodEnd, domainProject, equipement, motive, status, id);
    }

    public static DomainAutorisation deleteInstance(DomainCollaborator domainCollaborator, DomainRequest domainRequest, Date periodStart, Date periodEnd, DomainProject domainProject, boolean equipement, String motive, String status, Long id) throws DomainException {
        return new DomainAutorisation(domainCollaborator, domainRequest, periodStart, periodEnd, domainProject, equipement, motive, status, id);
    }

     /*
    * GETTERS
    * */

    public Long getId() { return id; }
    public DomainCollaborator getDomainCollaborator() {     return domainCollaborator;    }
    public DomainRequest getDomainRequest() {        return domainRequest;    }
    public Date getPeriodStart() {return periodStart;}
    public Date getPeriodEnd() {return periodEnd;}
    public DomainProject getDomainProject() {return domainProject;}
    public boolean isEquipement() {return equipement;}
    public String getMotive() {return motive;}
    public String getStatus() {
        return status;
    }

    /*
     *SETTERS
     */
    public void setId( Long id) { this.id = id; }
    public void setDomainCollaborator(DomainCollaborator domainCollaborator) {this.domainCollaborator = domainCollaborator;}
    public void setDomainRequest(DomainRequest domainRequest) {this.domainRequest = domainRequest;}
    public void setPeriodStart(Date periodStart) { this.periodStart = periodStart; }
    public void setPeriodEnd(Date periodEnd) {this.periodEnd = periodEnd;}
    public void setDomainProject(DomainProject domainProject) {this.domainProject = domainProject;}
    public void setEquipement(boolean equipement) {this.equipement = equipement;}
    public void setMotive(String motive) {this.motive = motive;}
    public void setStatus(String status) {        this.status = status;    }


	@Override
	public String toString() {
		return "DomainAutorisation [id=" + id + ", domainCollaborator=" + domainCollaborator.toString() + ", domainRequest="
				+ domainRequest.toString() + ", periodStart=" + periodStart + ", periodEnd=" + periodEnd + ", domainProject="
				+ domainProject.toString() + ", equipement=" + equipement + ", motive=" + motive + ", status=" + status + "]";
	}
    
    
}
