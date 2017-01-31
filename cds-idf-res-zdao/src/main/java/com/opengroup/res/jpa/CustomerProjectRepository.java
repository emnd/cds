package com.opengroup.res.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.opengroup.res.jpa.entities.Customer;
import com.opengroup.res.jpa.entities.CustomerProject;
import com.opengroup.res.jpa.entities.Project;

public interface CustomerProjectRepository extends CrudRepository<CustomerProject, Long>{

	 @Query ("SELECT p FROM CustomerProject p WHERE p.project = ?0")
	    List<CustomerProject> searchByProject(Project project);


	    List<CustomerProject> findAll();

	    @Query("SELECT cl FROM CustomerProject cl WHERE cl.customer = ?0")
	    List<CustomerProject> findByCustomer(Customer customer);


}
