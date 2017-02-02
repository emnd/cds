package com.opengroup.res.core.domain;

import java.io.Serializable;

/**
 * ODEADOM DomainProject model. A parameter is identified by its context and its code and have a related value. Note that it's a generic solution to materialize very simple
 * data. When data representation properties are growing, think of a dedicated class object. Other tip : ff something is materialized in a project structure repository, you
 * can also define a type class object, it is design for extension .. So make the good choice ...
 *
 *
 * @author Open groupe
 * @since 1.0.0
 */
public class DomainCustomer extends DomainBeanTrackable implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  Long idCustomer;
    private String nameCustomer; 

    public DomainCustomer( Long idCustomer ,String nameCustomer) throws DomainException {
		super();
    	if(idCustomer == null) {
            constraintsErrors.add( "DomainCustomer idCustomer unsatisfied rule" );
        }
    	 if(nameCustomer==null)
    	 {
    		 constraintsErrors.add( "DomainCustomer state unsatisfied rule" ); 
    	 }
       
//        if(constraintsErrors.size() > 0){
//            throw new DomainException("" );
//        }
		this.idCustomer = idCustomer;
		this.nameCustomer = nameCustomer;
			
	}

	public DomainCustomer(Long idCustomer) {
		
		this.idCustomer = idCustomer;
	}

	public static DomainCustomer createCustomer(String nameCustomer) throws DomainException {
        return new DomainCustomer(null, nameCustomer);
    }

  
    public static DomainCustomer updateCustomer(Long idCustomer, String nameCustomer)throws DomainException {
        return new DomainCustomer( idCustomer, nameCustomer);
    }

  
    public static DomainCustomer deleteCustomer(Long idCustomer) throws DomainException {
        return new DomainCustomer(idCustomer);
    }

    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCustomer == null) ? 0 : idCustomer.hashCode());
		result = prime * result + ((nameCustomer == null) ? 0 : nameCustomer.hashCode());
		
		return result;
	}



	public Long getIdCustomer() {
		return idCustomer;
	}


	public String getNameCustomer() {
		return nameCustomer;
	}

   
}
