package com.opengroup.res;

import com.opengroup.res.organization.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is the main class which launch jobs execution
 *
 * @author Open group
 * @since 1.0.0
 */
@SpringBootApplication
public class Application {

	@Autowired
	private UserServices userServices;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
}
