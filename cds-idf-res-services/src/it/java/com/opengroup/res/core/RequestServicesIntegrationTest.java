package com.opengroup.res.core;

import java.text.SimpleDateFormat;
//import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.opengroup.res.core.RequestServices;

/**
 * Integration test on request
 *
 * @author Open groupe
 * @since 3.0.0
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RequestServicesIntegrationTest {
	
	@Autowired
    private RequestServices requestServices;

//    @Autowired
//    private CollaboratorRepository collaboratorRepository;
    
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
  /*  @Test
    @Commit
    public void testCreate() throws Exception {
    	requestServices.createRequest("String applicant 3", "String decider 3", sdf.parse("07/02/2037"), sdf.parse("32/02/2037"));
 
    	requestServices.createRequest("String applicant 4", "String decider 4",sdf.parse("07/02/2037"), sdf.parse("34/02/2037"));
    } */

   /* @Test
    @Commit
    public void testUpdate() throws Exception {
        
        requestServices.updateRequest(2L,"philippe", "Sartre", sdf.parse("06/02/2017"), sdf.parse("06/02/2039"));

    } */


    @Test
    @Commit
    public void testDelete() throws Exception {
        requestServices.deleteRequest(4L,"String applicant 4", "String decider 4",sdf.parse("07/02/2037"), sdf.parse("34/02/2037"));
    }


}
