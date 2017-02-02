package com.opengroup.res.core.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by MLA13933 on 08/11/2016.
 */
@RunWith(JUnit4.class)
public class DomainParameterTest {

    @Test
    public void newCreatedStateInstance() throws DomainException {
        DomainParameter domainParameter = DomainParameter.newCreatedStateInstance(DomainParameter.ParameterContext.PRODUCT, "BA45654", "Banane", "RCH11270");
        Assert.assertEquals("CREATED", domainParameter.getState().toString());
    }


    @Test
    public void newModifiedStateInstance() throws DomainException {

        DomainParameter domainParameter = DomainParameter.newModifiedStateInstance(DomainParameter.ParameterContext.PRODUCT, "BA45654", "Banane", "RCH11270");
        Assert.assertEquals("MODIFIED", domainParameter.getState().toString());

    }

    @Test
    public void newSuppressedStateInstance() throws DomainException {

        DomainParameter domainParameter = DomainParameter.newSuppressedStateInstance(DomainParameter.ParameterContext.PRODUCT, "BA45654", "RCH11270");
        Assert.assertEquals("SUPPRESSED", domainParameter.getState().toString());

    }

    @Test
    public void newValidatedStateInstance() throws DomainException {

        DomainParameter domainParameter = DomainParameter.newValidatedStateInstance(DomainParameter.ParameterContext.PRODUCT, "BA45654", "Banane", "RCH11270");

        Assert.assertEquals("VALIDATED", domainParameter.getState().toString());

    }

    @SuppressWarnings("unused")
	@Test(expected = DomainException.class)
    public void newCreatedStateInstance2() throws DomainException {

        DomainParameter domainParameter2 = DomainParameter.newCreatedStateInstance(null, "BA45654", "Banane", "RCH11270");
        DomainParameter domainParameter3 = DomainParameter.newCreatedStateInstance(DomainParameter.ParameterContext.PRODUCT, "BA45654", null, "RCH11270");
        DomainParameter domainParameter4 = DomainParameter.newCreatedStateInstance(DomainParameter.ParameterContext.PRODUCT, "BA45654", "Banane", null);
    }

}