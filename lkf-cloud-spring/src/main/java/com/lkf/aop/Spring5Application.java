package com.lkf.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author kaifeng
 */
@SpringBootApplication(scanBasePackages = "com.lkf")
public class Spring5Application {

    public static void main(String[] args) {
        SpringApplication.run(Spring5Application.class, args);
    }
}
