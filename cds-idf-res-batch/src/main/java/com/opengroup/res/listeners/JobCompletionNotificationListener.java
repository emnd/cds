package com.opengroup.res.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

/**
 * A listener which trace the final state of the execution job
 *
 * @author Open group
 * @since 1.0.0
 */
@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	private static final Logger LOGGER = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			LOGGER.info("!!! JOB FINISHED! Verify the results!");
		}
	}
}
