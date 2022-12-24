package com.team1.TBBCloneCoding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TbbCloneCodingApplication {
	public static void main(String[] args) {
		SpringApplication.run(TbbCloneCodingApplication.class, args);
	}
}
