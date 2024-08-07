package com.cybage.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(scanBasePackages = "com.cybage")
@EnableJpaRepositories("com.cybage.dao")
@EntityScan("com.cybage.entity")
public class EmployeeMgmtPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeMgmtPortalApplication.class, args);
	}

	// Configure multipart resolver to handle file uploads
	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}

	// Configure resource handlers to serve uploaded resumes
	@Bean
	public WebMvcConfigurer webMvcConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addResourceHandlers(ResourceHandlerRegistry registry) {
				registry.addResourceHandler("/resumes/**").addResourceLocations("file:resumes/");
			}
		};
	}
}
