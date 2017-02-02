package com.opengroup.res.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * ODEADOM DomainProject model. A parameter is identified by its context and its code and have a related value. Note that it's a generic solution to materialize very simple
 * data. When data representation properties are growing, think of a dedicated class object. Other tip : ff something is materialized in a project structure repository, you
 * can also define a type class object, it is design for extension .. So make the good choice ...
 *
 *
 * @author Open groupe
 * @since 1.0.0
 */
public class DomainProject extends DomainBeanTrackable implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static Long idProject;
    private String nameProject;
    private Date periodStart;
    private Date periodEnd;
   

    public DomainProject( Long idProject ,String nameProject, Date periodStart,
			Date periodEnd) throws DomainException {
		super();
    	if(idProject == null) {
            constraintsErrors.add( "DomainProject idProject unsatisfied rule" );
        }
    	 if(nameProject==null)
    	 {
    		 constraintsErrors.add( "DomainProject state unsatisfied rule" ); 
    	 }
       
//        if(constraintsErrors.size() > 0){
//            throw new DomainException("" );
//        }
		this.idProject = idProject;
		this.nameProject = nameProject;
		this.periodStart = periodStart;
		this.periodEnd = periodEnd;
		
	}

	public DomainProject(Long idProject) {
		
		this.idProject = idProject;
	}

	public static DomainProject createProject(String nameProject, Date periodStart, Date periodEnd) throws DomainException {
        return new DomainProject(idProject, nameProject, periodStart, periodEnd);
    }

    public static DomainProject updateProject(Long idProject, String nameProject, Date periodStart, Date periodEnd)throws DomainException {
        return new DomainProject( idProject, nameProject,  periodStart,  periodEnd);
    }
  
    public static DomainProject deleteProject(Long idProject) throws DomainException {
        return new DomainProject(idProject);
    }
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idProject == null) ? 0 : idProject.hashCode());
		result = prime * result + ((nameProject == null) ? 0 : nameProject.hashCode());
		result = prime * result + ((periodEnd == null) ? 0 : periodEnd.hashCode());
		result = prime * result + ((periodStart == null) ? 0 : periodStart.hashCode());
		return result;
	}

	public Long getIdProject() {
		return idProject;
	}

	public String getNameProject() {
		return nameProject;
	}

	public Date getPeriodStart() {
		return periodStart;
	}

	public Date getPeriodEnd() {
		return periodEnd;
	}
   
}
