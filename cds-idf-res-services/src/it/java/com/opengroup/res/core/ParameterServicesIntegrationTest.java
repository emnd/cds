package com.opengroup.res.core;

import com.opengroup.res.core.domain.DomainParameter;
import com.opengroup.res.jpa.ParameterRepository;
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
public class ParameterServicesIntegrationTest {

    @Autowired
    private ParameterServices parameterServices;

    @Autowired
    private ParameterRepository parameterRepository;


    @Test
    @Commit
    public void testCreate() throws Exception {
        parameterServices.createParameter(DomainParameter.ParameterContext.PRODUCT, "CAN", "Canne à sucre", "RCH11270");
    }

    @Test
    @Commit
    public void testUpdate() throws Exception {
        parameterServices.updateParameter(DomainParameter.ParameterContext.PRODUCT, "CAN", "Canne à sucre", "RCH11270");
    }


    @Test
    @Commit
    public void testDelete() throws Exception {
        parameterServices.deleteParameter(DomainParameter.ParameterContext.PRODUCT, "CAN","RCH11270");
    }

}