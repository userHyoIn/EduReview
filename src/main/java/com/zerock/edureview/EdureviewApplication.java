package com.zerock.edureview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EdureviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdureviewApplication.class, args);
	}

}
