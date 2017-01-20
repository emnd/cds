package com.opengroup.res.jpa;

import com.opengroup.res.jpa.entities.ParameterPK;
import com.opengroup.res.jpa.entities.Parameter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * The repository interface to manage Parameter entity
 *
 * @author Open groupe
 * @since 1.0.0
 */
public interface ParameterRepository extends CrudRepository<Parameter, ParameterPK> {
    /**
     *
     * @return
     */
    List<Parameter> findAll();

    /**
     *
     * @param code
     * @return
     */
    @Query("SELECT paramValue from Parameter p WHERE p.id.code = ?1")
    String findByCode(String code);

}
