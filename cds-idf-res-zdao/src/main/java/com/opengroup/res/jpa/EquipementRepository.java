package com.opengroup.res.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.opengroup.res.jpa.entities.Equipement;

/**
 * @author OAI15274
 *date début:22/12/2016
 */

public interface EquipementRepository extends CrudRepository<Equipement, Long> {

	List<Equipement> findAll();
	
	@Query("Select e FROM Equipement e where e.collaborator IS NULL")
	List<Equipement> findAllWithoutCollab();
}
