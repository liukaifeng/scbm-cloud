package com.lkf.config.repository;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * mongodb 存储自动配置类
 *
 * @author 刘凯峰
 * @date 2018-10-29 18-03
 */
@Configuration
@Profile("mongo")
@ConditionalOnClass(MongoTemplate.class)
public class MongoRepositoryConfiguration {
}
