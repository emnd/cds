package com.opengroup.res.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

//import com.opengroup.res.jpa.entities.Collaborator;
import com.opengroup.res.jpa.entities.Location;

/**
 * @author KAI15273
 *
 */

public interface LocationRepository extends CrudRepository <Location, Long>
{

    @Query("SELECT l FROM Location l WHERE "
            + "LOWER(l.name) = LOWER(?1) "
            + "AND LOWER(l.block) = LOWER(?2) "
            + "AND LOWER(l.place) = LOWER(?3) "
    )
    List<Location> findByNameAndBlockAndPlace(String nameLocation,String blockLocation, String place);

    List<Location> findAll();
}
