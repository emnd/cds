/**
 * 
 */
package com.opengroup.res.jpa.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//import javax.persistence.Access;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.opengroup.res.util.EntityBean;

/**
 * @author SGU15268
 *
 *L'application est basique et gère des clients [Customer]. La classe [Customer] fait partie de la couche [JPA] et est la suivante :
 */
    /*annotation JPA qui fait que la persistance des instances [Customer] (Create, Read, Update, Delete) va être gérée
par une implémentation JPA. D'après les dépendances Maven, on voit que c'est l'implémentation JPA / Hibernate qui est
utilisée ;*/

@Entity 
@Table(name="customer")

public class Customer  implements Serializable , EntityBean {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idCustomer;
	private String nameCustomer;
	private int version;
	private List<CustomerProject> cps = new ArrayList<CustomerProject>();
	

	public Customer() {
		
	}

	public Customer(String nameCustomer) {
		
		this.nameCustomer = nameCustomer;
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="idCustomer")
	public Long getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(Long idCustomer) {
		this.idCustomer = idCustomer;
	}

	@Column(name="nom")
	public String getNameCustomer() {
		return nameCustomer;
	}

	public void setNameCustomer(String nameCustomer) {
		this.nameCustomer = nameCustomer;
	}
	
	@Version
    @Column(name="version")
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
		
	/**
	 * @return the cps  like customerprojects
	 */
	@OneToMany(mappedBy="customer")
	public List<CustomerProject> getCps() {
		return cps;
	}

	/**
	 * @param cps the cps to set
	 */
	public void setCps(List<CustomerProject> cps) {
		this.cps = cps;
	}  

	@Override
	public String toString() {
		return "Customer [idCustomer=" + idCustomer + ", nameCustomer=" + nameCustomer + ", version=" + version + "]";
	}

	

}
