package com.lkf.gateway.config;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * @author kaifeng
 */
@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = "com.lkf")
@PropertySource(value = "classpath:config/application.properties")
public class CmpGatewayApplication {

    public static void main( String[] args ) {
        SpringApplication.run(CmpGatewayApplication.class, args);
    }
}
