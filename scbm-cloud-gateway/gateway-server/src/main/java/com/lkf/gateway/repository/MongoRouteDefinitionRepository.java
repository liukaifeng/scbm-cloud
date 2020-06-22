package com.scbm.gateway.repository;

import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.data.mongodb.core.MongoTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.*;

import static java.util.Collections.synchronizedMap;

/**
 * 使用Mongo保存自定义路由配置（代替默认的InMemoryRouteDefinitionRepository）
 * <p/>
 * 首次调用会把自定义路由配置信息加载到缓存中，以后的每次调用都从缓存返回
 *
 * @author 刘凯峰
 * @date 2018-11-29 10-48
 */
public class MongoRouteDefinitionRepository implements RouteDefinitionRepository {
    private final Map<String, RouteDefinition> routes = synchronizedMap(new LinkedHashMap<String, RouteDefinition>());

    private final MongoTemplate mongoTemplate;
    private final String mongoCollection;

    public MongoRouteDefinitionRepository(MongoTemplate mongoTemplate,String mongoCollection ) {
        this.mongoTemplate = mongoTemplate;
        this.mongoCollection = mongoCollection;
    }

    /**
     * 获取自定义路由信息
     *
     * @author 刘凯峰
     * @date 2018/11/29 12:00
     */
    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        //判断本地缓存是否为空，不为空直接返回
        if (Objects.nonNull(routes) && routes.size() > 0) {
            return getRoutes();
        }
        //从mongodb获取自定义路由信息
        List<MongoRouteDefinition> mongoRouteDefinitionList = mongoTemplate.findAll(MongoRouteDefinition.class, mongoCollection);
        List<RouteDefinition> routeDefinitions = new ArrayList<>();
        //转换为路由定义对象并存入缓存中
        mongoRouteDefinitionList.forEach(mongoRouteDefinition -> {
            RouteDefinition routeDefinition = new RouteDefinition();
            routeDefinition.setId(mongoRouteDefinition.getRouteId());
            routeDefinition.setFilters(mongoRouteDefinition.getFilters());
            routeDefinition.setPredicates(mongoRouteDefinition.getPredicates());
            routeDefinition.setUri(URI.create(mongoRouteDefinition.getUri()));
            routeDefinition.setOrder(mongoRouteDefinition.getOrder());
            routes.put(routeDefinition.getId(), routeDefinition);
        });
        return Flux.fromIterable(routeDefinitions);
    }

    /**
     * 新增路由信息
     *
     * @param route 路由定义对象
     * @return reactor.core.publisher.Mono<java.lang.Void>
     * @author 刘凯峰
     * @date 2018/11/29 13:08
     */
    @Override
    public Mono<Void> save( Mono<RouteDefinition> route ) {
        return route.flatMap(routeDefinition -> {
            MongoRouteDefinition mongoRouteDefinition = new MongoRouteDefinition();
            mongoRouteDefinition.setRouteId(routeDefinition.getId());
            mongoRouteDefinition.setPredicates(routeDefinition.getPredicates());
            mongoRouteDefinition.setFilters(routeDefinition.getFilters());
            mongoRouteDefinition.setUri(routeDefinition.getUri().toString());
            mongoRouteDefinition.setOrder(routeDefinition.getOrder());
            mongoTemplate.save(mongoRouteDefinition, mongoCollection);
            routes.put(routeDefinition.getId(), routeDefinition);
            return Mono.empty();
        });

    }

    /**
     * 删除路由信息
     *
     * @param routeId 路由id
     * @return reactor.core.publisher.Mono<java.lang.Void>
     * @author 刘凯峰
     * @date 2018/11/29 13:09
     */
    @Override
    public Mono<Void> delete( Mono<String> routeId ) {
        return routeId.flatMap(id -> {
            if (routes.containsKey(id)) {
                routes.remove(id);
                return Mono.empty();
            }
            return Mono.defer(() -> Mono.error(new NotFoundException("RouteDefinition not found: " + routeId)));
        });
    }

    private Flux<RouteDefinition> getRoutes() {
        return Flux.fromIterable(routes.values());
    }

}
