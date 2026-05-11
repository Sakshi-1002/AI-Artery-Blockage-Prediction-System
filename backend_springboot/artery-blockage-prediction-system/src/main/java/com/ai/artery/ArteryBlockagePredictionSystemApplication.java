package com.ai.artery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ArteryBlockagePredictionSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArteryBlockagePredictionSystemApplication.class, args);
	}

}
