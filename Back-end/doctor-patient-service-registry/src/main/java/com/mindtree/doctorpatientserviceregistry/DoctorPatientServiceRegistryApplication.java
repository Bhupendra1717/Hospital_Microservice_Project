package com.mindtree.doctorpatientserviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DoctorPatientServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoctorPatientServiceRegistryApplication.class, args);
	}

}
