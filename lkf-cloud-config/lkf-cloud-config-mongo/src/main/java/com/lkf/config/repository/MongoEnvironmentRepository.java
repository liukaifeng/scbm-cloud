package com.lkf.config.repository;

import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.core.Ordered;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 根据请求参数查询配置
 *
 * @author 刘凯峰
 * @date 2018-10-29 17-58
 */
public class MongoEnvironmentRepository implements EnvironmentRepository, Ordered {
    private MongoTemplate mongoTemplate;
    private final static String DEFAULT_PREFIX = "default";
    private final static String MONGO_CONFIG_COLLECTION = "test";

    MongoEnvironmentRepository( MongoTemplate mongoTemplate ) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Environment findOne( String application, String profile, String label ) {
        if (StringUtils.isEmpty(profile)) {
            profile = DEFAULT_PREFIX;
        }
        if (!profile.startsWith(DEFAULT_PREFIX)) {
            profile = DEFAULT_PREFIX + "," + profile;
        }
        //逗号分隔转成数组
        String[] profiles = StringUtils.commaDelimitedListToStringArray(profile);
        //Environment 对象构建
        Environment environment = new Environment(application, profiles, label, null,
                null);
        return getConfFromMongodb(application, profiles, label, environment);
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE - 10;
    }

    /**
     * 从mongodb中查询配置项添加到对象Environment中
     *
     * @param application 应用名称
     * @param profiles    环境
     * @param label       版本
     * @param environment 环境上下文对象
     * @return org.springframework.cloud.config.environment.Environment
     * @author 刘凯峰
     * @date 2018/10/30 14:16
     */
    private Environment getConfFromMongodb( String application, String[] profiles, String label, Environment environment ) {
        //根据应用名称，环境，版本号，查询对应的配置信息
        MongoEnvironment mongoEnvironment = search(application, profiles, label);
        if (Objects.isNull(mongoEnvironment)) {
            return environment;
        }
        Map<String, String> map = new LinkedHashMap<>();
        //将配置项conf添加到map中
        if (Objects.nonNull(mongoEnvironment.getConf())) {
            map.putAll(mongoEnvironment.getConf());
        }
        //解析confRef，转换成键值对
        if (Objects.nonNull(mongoEnvironment.getConfRef())) {
            mongoEnvironment.getConfRef().forEach(confRef -> {
                //查询引用配置信息
                MongoEnvironment confRefEnvironment = search(confRef, profiles, label);
                if (Objects.nonNull(confRefEnvironment.getConf())) {
                    map.putAll(confRefEnvironment.getConf());
                }
            });
        }
        environment.add(new PropertySource(mongoEnvironment.getApplication() + "-" + mongoEnvironment.getProfile(), map));
        return environment;
    }

    /**
     * 根据应用名称、环境、版本，查询配置信息
     *
     * @param application 应用名称
     * @param profiles    环境
     * @param label       版本
     * @return com.lkf.config.repository.MongoEnvironment
     * @author 刘凯峰
     * @date 2018/10/30 14:11
     */
    private MongoEnvironment search( String application, String[] profiles, String label ) {
        //查询条件拼接
        Query query = new Query();
        query.addCriteria(Criteria.where("application").is(application)
                .and("profile").in(profiles)
                .and("label").is(label));
        //默认按照优先级降序
        query.with(new Sort(Sort.Direction.DESC, "priority"));
        //请求查询
        return mongoTemplate.findOne(query, MongoEnvironment.class, MONGO_CONFIG_COLLECTION);
    }
}
