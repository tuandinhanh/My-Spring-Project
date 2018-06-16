package com.anhtuan.springmvc.CRMSpringMVC.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.anhtuan.springmvc.CRMSpringMVC.dao")
@EnableTransactionManagement
public class WebConfig {
}
