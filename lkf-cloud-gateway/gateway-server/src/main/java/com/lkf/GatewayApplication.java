package com.lkf;


import com.alibaba.fastjson.JSONObject;
import com.lkf.filter.AuthorizeFilter;
import com.lkf.filter.AuthorizeGatewayFilter;
import com.lkf.filter.RedirectGatewayFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.filter.factory.RequestHeaderToRequestUriGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

/**
 * @author kaifeng
 */
@SpringBootApplication
@EnableEurekaClient
public class GatewayApplication {
    private final DynamicGatewayService dynamicGatewayService;

    @Autowired
    public GatewayApplication( DynamicGatewayService dynamicGatewayService ) {
        this.dynamicGatewayService = dynamicGatewayService;
    }

    public static void main( String[] args ) {
        SpringApplication.run(GatewayApplication.class, args);

    }

//    @Bean
//    public RouterFunction<ServerResponse> testFunRouterFunction() {
//        RouterFunction<ServerResponse> route = RouterFunctions.route(
//                RequestPredicates.path("/test"), request -> ServerResponse.ok()
//                        .body(BodyInserters.fromObject("I am testing")));
//        return route;
//    }

//    @Bean
//    public RouteLocator customRouteLocator( RouteLocatorBuilder builder ) {
//        return builder.routes().route(r -> r.path("/user/**").uri("lb://user-service"))
//                .build();
//    }

    /**
     * 路由规则动态注入
     * @author 刘凯峰
     * @date 2018/11/22 16:51
     * @return void
     */
    @PostConstruct
    public void load() {
        dynamicGatewayService.loadRoutes();
    }




}
