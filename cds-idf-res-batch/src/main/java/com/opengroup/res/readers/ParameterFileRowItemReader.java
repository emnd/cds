package com.opengroup.res.readers;

import com.opengroup.res.model.ParameterFileRow;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.Resource;

/**
 * Typed reader <code>extends FlatFileItemReader</code> to abstract the logic of mapping between
 * a resource row and its class model
 *
 * @author Open group
 * @since 1.0.0
 */
public class ParameterFileRowItemReader extends FlatFileItemReader<ParameterFileRow> {

    public ParameterFileRowItemReader(Resource resource) {
        this.setResource(resource);
        this.setLineMapper(new DefaultLineMapper<ParameterFileRow>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "context", "key", "value" });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<ParameterFileRow>() {{
                setTargetType(ParameterFileRow.class);
            }});
        }});
    }
}
