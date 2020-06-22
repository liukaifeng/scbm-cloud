package com.scbm;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author kaifeng
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }



    @GetMapping("/user")
    public Mono<String> getUser() {
        return Mono.just("I'm ok");
    }

    @Autowired
    private DiscoveryClient discoveryClient;
    @GetMapping("/user/instances")
    public Mono<String> getinstances() {
        return Mono.just(JSONObject.toJSONString(discoveryClient.getInstances("user-service")));
    }

}
