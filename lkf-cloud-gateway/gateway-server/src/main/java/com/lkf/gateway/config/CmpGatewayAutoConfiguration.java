package com.lkf.gateway.config;

import com.lkf.gateway.repository.MongoRouteDefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * 计算平台网关自动配置对象
 *
 * @author 刘凯峰
 * @date 2018-11-29 13-33
 */
@Configuration
@Import(CmpGatewayProperties.class)
public class CmpGatewayAutoConfiguration {

    public final MongoTemplate mongoTemplate;

    @Autowired
    public CmpGatewayAutoConfiguration( MongoTemplate mongoTemplate ) {
        this.mongoTemplate = mongoTemplate;
    }

    @Bean
    public CmpGatewayProperties gatewayProperties() {
        return new CmpGatewayProperties();
    }

    @Bean
    public MongoRouteDefinitionRepository mongoRouteDefinitionRepository( CmpGatewayProperties gatewayProperties ) {
        return new MongoRouteDefinitionRepository(mongoTemplate, gatewayProperties.getCollection());
    }
}
