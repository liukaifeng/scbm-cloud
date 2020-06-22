package com.scbm.gateway.config;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * @author kaifeng
 */
@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = "com.scbm")
@PropertySource(value = "classpath:config/application.properties")
public class Application {

    public static void main( String[] args ) {
        SpringApplication.run(Application.class, args);
    }
}
