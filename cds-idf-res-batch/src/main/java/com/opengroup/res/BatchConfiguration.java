package com.opengroup.res;

import com.opengroup.res.core.domain.DomainParameter;
import com.opengroup.res.processors.ParameterItemProcessor;
import com.opengroup.res.readers.ParameterFileRowItemReader;
import com.opengroup.res.writers.ParameterServicesItemWriter;
import com.opengroup.res.model.ParameterFileRow;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

/**
 * ImportParameterJob Java configuration
 *
 * @author Open group
 * @since 1.0.0
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Value("classpath:parameter-data.csv")
	private Resource inputFile;

	@Bean
	public ItemReader<ParameterFileRow> reader() {
		return  new ParameterFileRowItemReader(inputFile);
	}

	@Bean
	public ItemProcessor<ParameterFileRow, DomainParameter> processor() {
		return new ParameterItemProcessor();
	}

	@Bean
	public ItemWriter<DomainParameter> writer() {
		return new ParameterServicesItemWriter();
	}

	@Bean
	public Job importUserJob(JobBuilderFactory jobs, Step s1, JobExecutionListener listener) {
		return jobs.get("ImportParameterJob")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.flow(s1)
				.end()
				.build();
	}

	@Bean
	public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<ParameterFileRow> reader,
	                  ItemWriter<DomainParameter> writer, ItemProcessor<ParameterFileRow, DomainParameter> processor) {
		return stepBuilderFactory.get("ETLStep")
				.<ParameterFileRow, DomainParameter> chunk(10)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.build();
	}
}
