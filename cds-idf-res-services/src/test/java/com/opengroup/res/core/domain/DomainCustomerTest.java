package com.opengroup.res.core.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by SGU on 30/01/2017.
 */
@RunWith(JUnit4.class)
public class DomainCustomerTest {
	   
	@SuppressWarnings("unused")
	@Test(expected = DomainException.class)
    public void createCustomer() throws DomainException, ParseException {
    	DomainCustomer domainCustomer = DomainCustomer.createCustomer("SNCF FRET");
        
    }
	

    @SuppressWarnings("unused")
	@Test(expected = DomainException.class)
    public void updateCustomer() throws DomainException, ParseException {

    	DomainCustomer domainCustomer = DomainCustomer.updateCustomer(19L,"RATP Ligne 14");
      

    }

    @SuppressWarnings("unused")
	@Test
    public void deleteCustomer() throws DomainException, ParseException {

    	DomainCustomer domainCustomer = DomainCustomer.deleteCustomer(18L);
        

    }

    @SuppressWarnings("unused")
	@Test(expected = DomainException.class)
    public void createCustomer2() throws DomainException, ParseException {

    	DomainCustomer domainCustomer1 = DomainCustomer.createCustomer("Momo");
    	DomainCustomer domainCustomer2 = DomainCustomer.createCustomer("Tata");
    	DomainCustomer domainCustomer3 = DomainCustomer.createCustomer("Rama");
    }

}