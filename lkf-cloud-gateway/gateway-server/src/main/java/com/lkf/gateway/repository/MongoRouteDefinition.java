package com.scbm.gateway.repository;

import lombok.Data;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

/**
 * 存储在mongodb中的自定义路由信息
 *
 * @author 刘凯峰
 * @date 2018-11-29 10-51
 */
@Data
public class MongoRouteDefinition {

    /**
     * 路由id
     */
    @Field("route_id")
    private String routeId;
    /**
     * 路由谓词
     */
    private List<PredicateDefinition> predicates = new ArrayList<>();
    /**
     * 过滤器
     */
    private List<FilterDefinition> filters = new ArrayList<>();
    /**
     * 跳转地址uri
     */
    private String uri;
    /**
     * 路由顺序
     */
    private int order;

}
