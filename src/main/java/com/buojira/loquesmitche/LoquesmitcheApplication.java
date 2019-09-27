package com.buojira.loquesmitche;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LoquesmitcheApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoquesmitcheApplication.class, args);
//		new BrokerJEEIntegrator().consume();
	}


}
