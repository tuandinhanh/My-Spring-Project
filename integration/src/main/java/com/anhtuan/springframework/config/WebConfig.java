package com.anhtuan.springframework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.io.File;
import java.util.Map;

@Configuration
@EnableWebMvc
@EnableIntegration
@ComponentScan(basePackages = "com.anhtuan.springframework")
public class WebConfig extends WebMvcConfigurationSupport {



}
