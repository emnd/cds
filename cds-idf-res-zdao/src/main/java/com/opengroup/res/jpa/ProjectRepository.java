package com.opengroup.res.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.opengroup.res.jpa.entities.Project;

public interface ProjectRepository extends CrudRepository<Project,Long>{

	
	Project findProjectByNameProject(String  nameProject);
	Project findOne(Long  id);
	boolean exists(Long id);

	//same search with JPQL
    @Query("SELECT p FROM Project p WHERE " +
            "LOWER(p.nameProject) LIKE LOWER(CONCAT('%',:searchTerm, '%')) ")
    List<Project> searchInNameProject(@Param("searchTerm") String searchTerm);


    List<Project> findAll();

    //JPQL does not have a LIMIT 1 option, so we have to return a list
//    @Query("SELECT p FROM Project p WHERE "
//            + "LOWER(p.nameProject) = LOWER(?1) "
//            
//        )
    Project findByNameProjectAndPeriodStartAndPeriodEnd(String nameProject,Date periodStart,Date periodEnd);
	

	
}
