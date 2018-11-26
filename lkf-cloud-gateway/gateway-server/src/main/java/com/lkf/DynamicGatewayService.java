package com.lkf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.InMemoryRouteDefinitionRepository;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 网关动态路由服务
 *
 * @author 刘凯峰
 * @date 2018-11-22 14-44
 */
@Component
public class DynamicGatewayService implements ApplicationEventPublisherAware {
    private final RouteDefinitionWriter routeDefinitionWriter;
    private ApplicationEventPublisher publisher;
    private final DynamicRouteProperties dynamicRouteProperties;

    @Autowired
    public DynamicGatewayService( DynamicRouteProperties dynamicRouteProperties ) {
        this.routeDefinitionWriter = new InMemoryRouteDefinitionRepository();
        this.dynamicRouteProperties = dynamicRouteProperties;
    }

    public Mono<Void> loadRoutes() {
        PredicateDefinition predicate = new PredicateDefinition();
        Map<String, String> predicateParams = new HashMap<>(8);
        predicate.setName("Path");
        predicateParams.put("pattern", "/user/**");
        predicate.setArgs(predicateParams);

        RouteDefinition definition = new RouteDefinition();
        definition.setId(dynamicRouteProperties.getRouteId());
        definition.setUri(URI.create("lb://".concat(dynamicRouteProperties.getServiceId())));
        definition.setPredicates(Collections.singletonList(predicate));

        routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
        return Mono.empty();
    }

    @Override
    public void setApplicationEventPublisher( ApplicationEventPublisher applicationEventPublisher ) {
        this.publisher = applicationEventPublisher;
    }


}


