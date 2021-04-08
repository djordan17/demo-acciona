package com.demo.acciona;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@EnableConfigurationProperties
@EnableJpaRepositories("com.demo.acciona.manage.infraestructure.tweet.repository")
@ComponentScan(basePackages = "com.demo.acciona")
@SpringBootApplication
public class DemoAccionaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoAccionaApplication.class, args);
	}

}
