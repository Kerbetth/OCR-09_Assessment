package com.medic.mediscreen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MediscreenAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediscreenAssessmentApplication.class, args);
	}

}
