package com.opengroup.res.processors;

import com.opengroup.res.core.domain.DomainParameter;
import com.opengroup.res.model.ParameterFileRow;
import org.springframework.batch.item.ItemProcessor;

/**
 * Transform an input source into an output destination ready to be load in a system such a database
 *
 * @author Open group
 * @since 1.0.0
 */
public class ParameterItemProcessor implements ItemProcessor<ParameterFileRow, DomainParameter> {

    @Override
    public DomainParameter process(ParameterFileRow item) throws Exception {
        String ctx = item.getContext().toUpperCase();
        DomainParameter.ParameterContext context = DomainParameter.ParameterContext.valueOf(ctx);
        return DomainParameter.newCreatedStateInstance(context, item.getKey(), item.getValue(), "AUTO");
    }
}
