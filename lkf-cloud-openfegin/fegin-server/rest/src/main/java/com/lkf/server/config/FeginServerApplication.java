package com.lkf.server.config;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * @author kaifeng
 */
@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = "com.lkf")
@PropertySource(value = "classpath:config/application.properties")
@EnableFeignClients(basePackages = "com.lkf")
public class FeginServerApplication {

    public static void main( String[] args ) {
        SpringApplication.run(FeginServerApplication.class, args);
    }
}
