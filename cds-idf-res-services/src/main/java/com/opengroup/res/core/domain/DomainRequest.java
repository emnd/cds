package com.opengroup.res.core.domain;


import com.opengroup.res.core.domain.DomainException;
import org.springframework.util.StringUtils;
import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import com.opengroup.res.core.domain.DomainAutorisation;


/**
 * Request model Class
 * @author Open group
 * @since 1.0.0
 */
public class DomainRequest extends DomainBeanTrackable implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private Date requestDate ;
    private Date replyDate;
    private String applicant;
    private String decider;
    private List<DomainAutorisation> domainAuthorisationList = new ArrayList<DomainAutorisation>();

    public DomainRequest(String applicant,Date requestDate) throws DomainException {

        if (StringUtils.isEmpty(applicant)) {
            constraintsErrors.add("An applicant must be provided");
        }

        if (requestDate == null) {
            constraintsErrors.add("A requestDate must be provided");
        }

        checkIfValid();
        this.applicant = applicant;
        this.requestDate = requestDate;

    }

    public DomainRequest(String applicant,String decider,Date requestDate,Date replyDate,Long id) throws DomainException {

        if (StringUtils.isEmpty(applicant)) {
            constraintsErrors.add("An applicant must be provided");
        }

        if (requestDate == null) {
            constraintsErrors.add("A requestDate must be provided");
        }

        checkIfValid();
        this.applicant = applicant;
        this.decider = decider;
        this.requestDate = requestDate;
        this.id = id;
        this.replyDate = replyDate;

    }

    public static DomainRequest newInstance(String applicant, String decider, Date requestDate, Date replyDate,Long id) throws DomainException {
        return new DomainRequest(applicant, decider, requestDate, replyDate,id);
    }

    public static DomainRequest updateInstance(String applicant, String decider, Date requestDate, Date replyDate,/* List<Authorisation> authorisationList,*/ Long id) throws DomainException {
        return new DomainRequest(applicant, decider, requestDate, replyDate/*, authorisationList*/,id);
    }

    public static DomainRequest deleteInstance(String applicant, String decider, Date requestDate, Date replyDate,/* List<Authorisation> authorisationList,*/ Long id) throws DomainException {
        return new DomainRequest(applicant, decider, requestDate, replyDate/*, authorisationList*/,id);
    }

    /**
     * Getters
     */
    public Long getId() {   return id;    }
    public Date getRequestDate() { return requestDate; }
    public Date getReplyDate() { return replyDate; }
    public String getDecider() { return decider; }
    public String getApplicant() {return applicant; }
    public List<DomainAutorisation> getDomainAuthorisationList() { return domainAuthorisationList; }
    /**
     * setters
     */

    public void setId(Long id) { this.id = id;    }
    public void setRequestDate(Date requestDate) { this.requestDate = requestDate; }
    public void setReplyDate(Date replyDate) { this.replyDate = replyDate;}
    public void setDecider(String decider) { this.decider = decider; }
    public void setApplicant(String applicant) { this.applicant = applicant; }
    public void setDomainAuthorisationList(List<DomainAutorisation> domainAuthorisationList) {     this.domainAuthorisationList = domainAuthorisationList;    }

	@Override
	public String toString() {
		return "DomainRequest [id=" + id + ", requestDate=" + requestDate + ", replyDate=" + replyDate + ", applicant="
				+ applicant + ", decider=" + decider + "]";
	}

    
}
