package com.opengroup.res.writers;

import com.opengroup.res.core.ParameterServices;
import com.opengroup.res.core.domain.DomainParameter;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * A custom <code>ItemWriter</code> which encapsulated our API service
 *
 * @author Open group
 * @since 1.0.0
 */
public class ParameterServicesItemWriter implements ItemWriter<DomainParameter> {

    @Autowired
    private ParameterServices parameterServices;

    @Override
    public void write(List<? extends DomainParameter> items) throws Exception {
        for(DomainParameter domainParameter : items){
            parameterServices.createParameter(domainParameter.getContext(), domainParameter.getKey(), domainParameter.getValue(), "AUTO");
        }
    }
}
