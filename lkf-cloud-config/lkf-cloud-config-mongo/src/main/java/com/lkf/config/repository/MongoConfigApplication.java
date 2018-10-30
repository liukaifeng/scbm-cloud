package com.lkf.config.repository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class MongoConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoConfigApplication.class, args);
	}
}
