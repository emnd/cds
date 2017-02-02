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
public class DomainProjectTest {
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
   
	@SuppressWarnings("unused")
	@Test(expected = DomainException.class)
    public void createProject() throws DomainException, ParseException {
    	DomainProject domainProject = DomainProject.createProject("CDS40", simpleDateFormat.parse("25/12/2016"), simpleDateFormat.parse("25/02/2017"));
        
    }
	

    @SuppressWarnings("unused")
	@Test(expected = DomainException.class)
    public void updateProject() throws DomainException, ParseException {

    	DomainProject domainProject = DomainProject.updateProject(19L,"CDS1", simpleDateFormat.parse("25/12/2016"), simpleDateFormat.parse("25/02/2017"));
      

    }

    @SuppressWarnings("unused")
	@Test
    public void deleteProject() throws DomainException, ParseException {

    	DomainProject domainProject = DomainProject.deleteProject(18L);
        

    }

    @SuppressWarnings("unused")
	@Test(expected = DomainException.class)
    public void createProject2() throws DomainException, ParseException {

    	DomainProject domainProject2 = DomainProject.createProject("CDS1", simpleDateFormat.parse("25/12/2016"), simpleDateFormat.parse("25/02/2017"));
    	DomainProject domainProject3 = DomainProject.createProject("CDS2", simpleDateFormat.parse("28/12/2016"), simpleDateFormat.parse("30/02/2017"));
    	DomainProject domainProject4 = DomainProject.createProject("CDS3", simpleDateFormat.parse("30/12/2016"), simpleDateFormat.parse("25/03/2017"));
    }

}