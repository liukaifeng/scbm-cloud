package com.scbm.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author kaifeng
 */
@SpringBootApplication(scanBasePackages = "com.scbm")
public class Spring5Application {

    public static void main(String[] args) {
        SpringApplication.run(Spring5Application.class, args);
    }
}
