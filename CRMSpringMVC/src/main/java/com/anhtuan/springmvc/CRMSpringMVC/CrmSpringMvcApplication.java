package com.anhtuan.springmvc.CRMSpringMVC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.anhtuan.springmvc.CRMSpringMVC.dao")
@SpringBootApplication
public class CrmSpringMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrmSpringMvcApplication.class, args);
	}
}
