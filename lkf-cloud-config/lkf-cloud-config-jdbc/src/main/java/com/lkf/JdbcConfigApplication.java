package com.lkf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class JdbcConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(JdbcConfigApplication.class, args);
	}
}
