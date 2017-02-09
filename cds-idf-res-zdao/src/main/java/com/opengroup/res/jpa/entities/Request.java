package com.opengroup.res.jpa.entities;
import java.io.Serializable;
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

import com.opengroup.res.util.EntityBean;

/**
 * Created by open groupe croissant.
 *
 * Model class for a request to the manager of the Centre de Service for the access authorisation for one or mode
 * "collaborators" - Open staff or external contractors
 */

@Entity
@Table(name="Request")
@NamedQuery(name = "Request.findAll", query = "SELECT r FROM Request r")
public class Request implements Serializable , EntityBean{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name="requestDate")
    @Temporal(TemporalType.DATE)    // Persist only the date, not the time
    private Date requestDate ;
    /**
     * Date of the LAST reply to this request.
     */
    @Column(name="replyDate")
    @Temporal(TemporalType.DATE)
    private Date replyDate;
    /**
     * The OpenID of the person at the origin of the request. Should point, using an auxilliary table, to a AppUser
     * object.
     */
    @Column(name="applicant")
    private String applicant;
    /**
     * The OpenID of the person that gave the last answer to this request. Should point, using an auxilliary table, to a AppUser
     * object which has a "decider" role.
     */
    @Column(name="decider")
    private String decider;
    /**
     * List of the Authorisations which are included (ie their FK points to) in this Request
     */

    @OneToMany (mappedBy = "request",cascade=CascadeType.ALL, orphanRemoval=true) // cascade=CascadeType.ALL, orphanRemoval=true pour la suppression en cascade (le 07 FEV 2017))
    private List<Authorisation> authorisations = new ArrayList<Authorisation>();


    public Request() {}

    //Constructor used for testing
    public Request(Date requestDate, Date replyDate) {
        this.requestDate = requestDate;
        this.replyDate = replyDate;
    }

    public Request(String applicant, Date requestDate,  List<Authorisation> authorisations) {
        this.requestDate = requestDate;
        this.applicant = applicant;
        this.authorisations = authorisations;
    }



    public Request(String applicant ,String decider, Date requestDate,  Date replyDate ) {
        this.applicant=applicant;
        this.decider=decider;
        this.requestDate = requestDate;
        this.replyDate = replyDate;
    }



    public Long getId() {
        return id;
    }

     public void setId(Long id) {
        this.id = id;
    }



    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(Date replyDate) {
        this.replyDate = replyDate;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getDecider() {
        return decider;
    }

    public void setDecider(String decider) {
        this.decider = decider;
    }

    public List<Authorisation> getAuthorisations() {
        return authorisations;
    }

    public void setAuthorisations(List<Authorisation> authorisations) {
        this.authorisations = authorisations;
    }
    
    

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", requestDate=" + requestDate +
                ", replyDate=" + replyDate +
                ", applicant='" + applicant + '\'' +
                ", decider='" + decider + '\'' +
                ", authorisations=" + authorisations +
                '}';
    }

	
}
