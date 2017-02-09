package com.opengroup.res.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.opengroup.res.core.CollaboratorServices;
import com.opengroup.res.core.domain.DomainCollaborator;
//import com.opengroup.res.core.domain.DomainCollaborator;
//import com.opengroup.res.core.domain.Domaincollaborator;
//import com.opengroup.res.jpa.CollaboratorRepository;


/**
 * Integration test on collaborator
 *
 * @author Open groupe
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class CollaboratorServicesIntegrationTest {
	
		@Autowired
	    private CollaboratorServices collaboratorServices;

//	    @Autowired
//	    private CollaboratorRepository collaboratorRepository;
	    
	 /*  @Test
	    @Commit
	    public void testCreate() throws Exception {
	    	collaboratorServices.createCollaborator("String loginOpen 1", "String firstName 1", "String lastName 1", "String emailOpen 1", "String bu 1");
	 
	    	collaboratorServices.createCollaborator("String loginOpen 3", "String firstName 3", "String lastName 3", "String emailOpen 2", "String bu 2");
	    } */

	/*    @Test
	    @Commit
	    public void testUpdate() throws Exception {
	        
	    	DomainCollaborator domainCollaborator = DomainCollaborator.updateInstance("String loginOpen Modifié", "String firstName Modifié", "String lastName Modifié", "String emailOpen Modifié", "String bu Modifié",2L);
	    	
	    	collaboratorServices.updateCollaborator(2L,
	    			"String loginOpen Modifié", 
	    			"String firstName Modifié", 
	    			"String lastName Modifié", 
	    			"String emailOpen Modifié", 
	    			"String bu Modifié"
	    			);
	    	System.out.println("\n"+"IdCollaborator : "+domainCollaborator.getId()+"\n");

	    	collaboratorServices.updateCollaborator(1L, "String loginOpen 4", "String firstName 4", "String lastName 4", "String emailOpen 4", "String bu 4");
	    }
*/

	 /*   @Test
	    @Commit
	    public void testDelete() throws Exception {
	        collaboratorServices.deleteCollaborator(3L, "String loginOpen 3", "String firstName 3", "String lastName 3", "String emailOpen 2", "String bu 2");
	    }
*/

}
