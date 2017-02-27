package com.opengroup.res.jpa.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.opengroup.res.util.EntityBean;



/**Data-model class for the authorisation required for a "collaborator" to access the CS
 *
 *
 * Created by EXT_IDA43 on 13/10/2016.
 */

@Entity
@Table(name="authorisation")
public class Authorisation implements Serializable , EntityBean{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * The id of the row in the table. Cannot be set or known before reading from the table.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id; // Separate id; too complicated to implement a composite PK
    @ManyToOne
    @JoinColumn(name = "idRequest")
    private Request request;    // Since it references the pk of another table, it is not nullable (at least at the moment of writing in the DB)
    @ManyToOne
    @JoinColumn(name = "idCollaborator")
    private Collaborator collaborator;
    @ManyToOne
    @JoinColumn(name = "idProject")
    private Project project;
    @Column(name="periodStart")
    @Temporal(TemporalType.DATE)
    private Date periodStart;
    @Column(name="periodEnd")
    @Temporal(TemporalType.DATE)
    private Date periodEnd;
    @Column(name="equipment")
    private boolean equipment;
    @Column(name="motive")
    private String motive;
    @Column(name="status")
    private String status;
    
    public Authorisation(){   }



    /**Full constructor
     *
     * @param request   the request which asks for this authorisation
     * @param collaborator  the object of this authorisation
     * @param periodStart
     * @param periodEnd
     * @param projectName   The project to which the collaborator shall be attached. Can be null
     * @param equipment     Whether the collaborator will bring his own equipment (a laptop, for example)
     * @param motive    The motive of the authorisation request
     */
    public Authorisation(Collaborator collaborator, Request request, Date periodStart, Date periodEnd,  boolean equipment, String motive, Project project) {
        this.request = request;
        this.collaborator = collaborator;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
        //this.projectName = projectName;
        this.equipment = equipment;
        this.motive = motive;
        this.project = project;
       
    }
//    Getters and setters
public Long getId() {
    return id;
}

public void setId(Long id) {this.id = id;}

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Collaborator getCollaborator() {
        return collaborator;
    }

    public void setCollaborator(Collaborator collaborator) {
        this.collaborator = collaborator;
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

   

    public boolean hasEquipment() {
        return equipment;
    }

    public void setEquipment(boolean equipment) {
        this.equipment = equipment;
    }

    public String getMotive() {
        return motive;
    }

    public void setMotive(String motive) {
        this.motive = motive;
    }

    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isEquipment() {
        return equipment;
    }

	@ManyToOne
	@JoinColumn(name="id_project")
    public Project getProject() {
  		return project;
  	}



  	public void setProject(Project project) {
  		this.project = project;
  	}


  

	@SuppressWarnings("unused")
	private class AuthorisationPK{

    }




}
