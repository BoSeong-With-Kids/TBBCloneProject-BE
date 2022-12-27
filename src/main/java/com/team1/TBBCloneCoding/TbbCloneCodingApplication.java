package com.team1.TBBCloneCoding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication
@EnableJpaAuditing
public class TbbCloneCodingApplication {
	public static void main(String[] args) {
		SpringApplication.run(TbbCloneCodingApplication.class, args);
	}
}
