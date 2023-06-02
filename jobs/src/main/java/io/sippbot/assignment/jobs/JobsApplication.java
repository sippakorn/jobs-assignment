package io.sippbot.assignment.jobs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class JobsApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobsApplication.class, args);
	}

}
