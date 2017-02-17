package com.opengroup.res.core;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Integration test on parameter
 *
 * @author Open groupe
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class ProjectServicesIntegrationTest {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    @Autowired
    private ProjectServices projectServices;


  /*  @Test
    @Commit
    public void testCreate() throws Exception {
    	projectServices.createProject("PROJET 1", new Date(), new Date());
    	projectServices.createProject("PROJET 2", sdf.parse("07/02/2017"), sdf.parse("25/02/2018"));
    	projectServices.createProject("PROJET 3", sdf.parse("20/12/2016"), sdf.parse("31/07/2017"));
    	projectServices.createProject("PROJET 4", sdf.parse("24/12/2016"), sdf.parse("24/02/2018"));
    } */

 /*  @Test    
    @Commit
    public void testUpdate() throws Exception {
    	projectServices.updateProject(54L,"Croissant", sdf.parse("27/12/2016"), sdf.parse("27/02/2017"));
    }
*/
   
 /*   @Test
    @Commit
    public void testDelete() throws Exception {
    	projectServices.deleteProject(56L,"PROJET 3", sdf.parse("20/12/2016"), sdf.parse("31/07/2017"));
    } */

}