package com.opengroup.res.core;

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
public class CustomerServicesIntegrationTest {
	
    @Autowired
    private CustomerServices customerServices;

//    @Test
//    @Commit
//    public void testCreate() throws Exception {
//    	customerServices.createCustomer("CDS9");
//    	customerServices.createCustomer("CDS10");
//    	customerServices.createCustomer("CDS11");
//    	customerServices.createCustomer("CDS12");
//    	customerServices.createCustomer("hghghghgh");
//    	
//    }

    @Test    
    @Commit
    public void testUpdate() throws Exception {
    	customerServices.updateCustomer(18L,"Con");
    }

//
//    @Test
//    @Commit
//    public void testDelete() throws Exception {
//    	customerServices.deleteCustomer(15L);
//    	System.out.println("hello world :" +15L);
//    }

}