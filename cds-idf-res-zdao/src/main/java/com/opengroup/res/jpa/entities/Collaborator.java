package com.opengroup.res.jpa.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
/**Data-model class for a "collaborator", a person whose presence inside the Centre de Services will be authorised
 * if the request is approved
 * Created by open groupe croissant.
 *
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.opengroup.res.util.EntityBean;

@Entity
@Table(name="collaborator")
@NamedQuery(name = "Collaborator.findAll", query = "SELECT c FROM Collaborator c")
public class Collaborator implements Serializable , EntityBean{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * The id of the row in the table. Cannot be set or known before reading from the table.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private Long id;
    /**
     * The id of the person inside the Open-groupe of type "EXT_IDA36". Can be null and can be changed so it is NOT used
     * for the equals() method.
     */
    @Column(name="login_open")
    private String loginOpen;
    @Column(name="last_name")
    private String lastName;
    @Column(name="first_name")
    private String firstName;
    @Column(name="email_open")
    private String emailOpen;
    @Column(name="bu_open")
    private String bu;
    @Version
    @Column(name="version")
    private int version;
    
	@OneToMany(mappedBy = "collaborator",cascade=CascadeType.ALL, orphanRemoval=true) // cascade=CascadeType.ALL, orphanRemoval=true pour la suppression en cascade (le 07 FEV 2017)
    private List<Authorisation> authorisations = new ArrayList<Authorisation>();
    
    public Collaborator() {
    }

//    public Collaborator(String prenom, String nom) {
//        this(null, prenom, nom);
//    }
    public Collaborator(String lastName, String firstName) {
    	this.firstName=firstName;
    	this.lastName=lastName;
    }
    public Collaborator(String loginOpen, String prenom, String nom, String emailOpen,String bu) {
        this.loginOpen = loginOpen;
        this.lastName = nom;
        this.firstName = prenom;
        this.emailOpen=emailOpen;
        this.bu=bu;
        
    }



//    Getters and setters

    public Long getId() {
        return id;
    }
    
    public String getEmailOpen() {
		return emailOpen;
	}

	public void setEmailOpen(String emailOpen) {
		this.emailOpen = emailOpen;
	}

	public String getLoginOpen() {
        return loginOpen;
    }

    public void setLoginOpen(String loginOpen) {
        this.loginOpen = loginOpen;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public boolean isAuthorised(){
        //TODO: Implement method
        return false;
    }
    

    public String getBu() {
		return bu;
	}

	public void setBu(String bu) {
		this.bu = bu;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public List<Authorisation> getAuthorisations() {
        return authorisations;
    }

    public void setAuthorisations(List<Authorisation> authorisations) {
        this.authorisations = authorisations;
    }

	
	

	@Override
    /**
     * A collaborator is identified by its first and last names (let's hope we won't have
     * two or more Jean Dupont)
     */
    // TODO Checks how Hibernate will save duplicate Collaborators given this definition of equals()
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Collaborator)) return false;

        Collaborator that = (Collaborator) o;

        if (!getLastName().equals(that.getLastName())) return false;
        return getFirstName().equals(that.getFirstName());
    }

    @Override
	public String toString() {
		return "Collaborator [id=" + id + ", loginOpen=" + loginOpen + ", lastName=" + lastName + ", firstName="
				+ firstName + ", emailOpen=" + emailOpen + ", bu=" + bu + "]";
	}

	//IDEA-generated
    @Override
    public int hashCode() {
        int result = getLastName().hashCode();
        result = 31 * result + getFirstName().hashCode();
        return result;
    }
}
