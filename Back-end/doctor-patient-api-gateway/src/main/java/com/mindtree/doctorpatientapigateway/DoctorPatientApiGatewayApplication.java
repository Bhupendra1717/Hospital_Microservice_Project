package com.mindtree.doctorpatientapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
@EnableEurekaClient
@CrossOrigin
public class DoctorPatientApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoctorPatientApiGatewayApplication.class, args);
	}
	
}
