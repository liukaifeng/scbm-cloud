package com.lkf.service;

import com.lkf.server.fegin.UserControllerFegin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * todo 一句话描述该类的用途
 *
 * @author 刘凯峰
 * @date 2018-12-13 11-25
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.lkf")
@EnableFeignClients(basePackages = "com.lkf")
@RestController
public class ServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }
    @Autowired
    private UserControllerFegin userControllerFegin;
    @GetMapping
    public String test(){
       return userControllerFegin.getUser("Hello");
    }
}
