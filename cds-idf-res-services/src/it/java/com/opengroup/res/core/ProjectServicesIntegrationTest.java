package com.opengroup.res.core;

import java.text.SimpleDateFormat;

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
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    @Autowired
    private ProjectServices projectServices;


//    @Test
//    @Commit
//    public void testCreate() throws Exception {
//    	projectServices.createProject("CDS9", simpleDateFormat.parse("25/12/2016"), simpleDateFormat.parse("25/02/2017"));
//    	projectServices.createProject("CDS10", simpleDateFormat.parse("25/12/2016"), simpleDateFormat.parse("25/02/2017"));
//    	projectServices.createProject("CDS11", simpleDateFormat.parse("25/12/2016"), simpleDateFormat.parse("25/02/2017"));
//    	projectServices.createProject("CDS12", simpleDateFormat.parse("25/12/2016"), simpleDateFormat.parse("25/02/2017"));
//    }

    @Test    
    @Commit
    public void testUpdate() throws Exception {
    	projectServices.updateProject(23L,"CDS8", simpleDateFormat.parse("27/12/2016"), simpleDateFormat.parse("27/02/2017"));
    }


//    @Test
//    @Commit
//    public void testDelete() throws Exception {
//    	projectServices.deleteProject(20L);
//    	System.out.println("hello world :" +20L);
//    }

}