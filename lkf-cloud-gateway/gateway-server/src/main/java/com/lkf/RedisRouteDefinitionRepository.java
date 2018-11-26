//package com.lkf;
//
//
//
//import com.alibaba.fastjson.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gateway.route.RouteDefinition;
//import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
//import org.springframework.cloud.gateway.support.NotFoundException;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
///**
// * 使用Redis保存自定义路由配置（代替默认的InMemoryRouteDefinitionRepository）
// * <p/>
// * 存在问题：每次请求都会调用getRouteDefinitions，当网关较多时，会影响请求速度，考虑放到本地Map中，使用消息通知Map更新。
// * @author 刘凯峰
// * @date 2018/11/22 14:49
// */
//@Component
//public class RedisRouteDefinitionRepository implements RouteDefinitionRepository {
//
//    public static final String  GATEWAY_ROUTES = "geteway_routes";
//    private final StringRedisTemplate redisTemplate;
//
//    @Autowired
//    public RedisRouteDefinitionRepository( StringRedisTemplate redisTemplate ) {
//        this.redisTemplate = redisTemplate;
//    }
//
//    @Override
//    public Flux<RouteDefinition> getRouteDefinitions() {
//        List<RouteDefinition> routeDefinitions = new ArrayList<>();
//        redisTemplate.opsForHash().values(GATEWAY_ROUTES).forEach(routeDefinition -> {
//            routeDefinitions.add(JSONObject.parseObject(routeDefinition.toString(), RouteDefinition.class));
//        });
//        return Flux.fromIterable(routeDefinitions);
//    }
//    @Override
//    public Mono<Void> save( Mono<RouteDefinition> route) {
//        return route
//                .flatMap(routeDefinition -> {
//                    redisTemplate.opsForHash().put(GATEWAY_ROUTES, routeDefinition.getId(),
//                            JSONObject.toJSONString(routeDefinition));
//                    return Mono.empty();
//                });
//    }
//    @Override
//    public Mono<Void> delete(Mono<String> routeId) {
//        return routeId.flatMap(id -> {
//            if (redisTemplate.opsForHash().hasKey(GATEWAY_ROUTES, id)) {
//                redisTemplate.opsForHash().delete(GATEWAY_ROUTES, id);
//                return Mono.empty();
//            }
//            return Mono.defer(() -> Mono.error(new NotFoundException("RouteDefinition not found: " + routeId)));
//        });
//    }
//}
