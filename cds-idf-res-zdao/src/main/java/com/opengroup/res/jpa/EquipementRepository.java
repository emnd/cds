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




	@Query("SELECT COUNT(*) FROM Equipement e WHERE e.stateType LIKE 'Available'" )
	Long getNbTotalPC();// Nombre total de PC disponibles

	@Query("SELECT COUNT(*) FROM Equipement e WHERE e.stateType LIKE 'Available' AND e.equipmentType Like 'Desktop'" )
	Long getNbTotalPCDesktop(); //Nombre total de PC fixe disponibles


	@Query("SELECT COUNT(*) FROM Equipement e WHERE e.stateType LIKE 'Available' AND e.equipmentType Like 'Laptop'" )
	Long getNbTotalPCLaptop(); //Nombre total de PC portable disponibles
/*
@Query("SELECT equipementType FROM Equipement e ")
List<String> findAllEqType();


@Query("SELECT stateType FROM Equipement e ")
List<String> findAllStateType();*/


}
