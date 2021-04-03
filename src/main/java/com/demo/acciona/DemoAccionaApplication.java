package com.demo.acciona;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableAutoConfiguration
@EnableConfigurationProperties
@SpringBootApplication
public class DemoAccionaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoAccionaApplication.class, args);
	}

}
