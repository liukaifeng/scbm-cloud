package com.scbm.config;


import com.mongodb.reactivestreams.client.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

/**
 * @author 刘凯峰
 * @version V1.0
 * @description mongodb 响应式编程
 * @date 2020-04-01 17:13
 */
@Configuration
public class ReactiveMongoConfig {

    @Autowired
    private MongoClient mongoClient;

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(mongoClient, "test");
    }
}
