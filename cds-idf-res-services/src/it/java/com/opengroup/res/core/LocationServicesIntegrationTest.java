package com.opengroup.res.core;

import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.opengroup.res.core.impl.mappers.LocationMapper;
import com.opengroup.res.jpa.HistoryLogRepository;
import com.opengroup.res.jpa.LocationRepository;

/**
 * Integration test on parameter
 *
 * @author Open groupe
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LocationServicesIntegrationTest {

	  @Autowired
	    private LocationServices locationServices;
   	
	   	@Autowired
	    private HistoryLogRepository historyLogRepository;

	    @Autowired
	    private LocationRepository locationRepository;

	    @Autowired
	    private LocationMapper locationMapper;

	SimpleDateFormat sdf=new SimpleDateFormat("YYYY/MM/DD");
	    
	 @Test
	   @Commit
	    public void testCreate() throws Exception {
	    	
	    	locationServices.createLocation("VIP 1", "CDS 1", "8ème étage");
	    	
	    
	        
	    	locationServices.createLocation("RAS 1", "SCRUM 1", "7ème étage");
	        
	    	locationServices.createLocation("RsS 1", "SCR 1", "5ème étage");
	    }
	    
//	    @Test
//	    @Commit
//	    public void testUpdate() throws Exception {
//	        locationServices.updateLocation("VIP", "CDS", "8ème étage");
//	    }
	//
	   
	   /* @Test
	    @Commit
	    public void testDelete() throws Exception {
	    	
	    
	       locationServices.deleteLocation(1L);
	      
	    }*/
}