package com.lkf.config.repository;

import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.core.Ordered;

/**
 * todo 一句话描述该类的用途
 *
 * @author 刘凯峰
 * @date 2018-10-29 17-58
 */
public class MongoEnvironmentRepository implements EnvironmentRepository, Ordered {
    @Override
    public Environment findOne( String application, String profile, String label ) {
        return null;
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE-10;
    }
}
