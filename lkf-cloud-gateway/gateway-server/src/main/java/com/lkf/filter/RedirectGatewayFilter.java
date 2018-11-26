package com.lkf.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * todo 一句话描述该类的用途
 *
 * @author 刘凯峰
 * @date 2018-11-22 17-00
 */
public class RedirectGatewayFilter implements GatewayFilter, Ordered {
    /**
     * Process the Web request and (optionally) delegate to the next
     * {@code WebFilter} through the given {@link GatewayFilterChain}.
     *
     * @param exchange the current server exchange
     * @param chain    过滤器链
     * @return {@code Mono<Void>} to indicate when request processing is complete
     */
    @Override
    public Mono<Void> filter( ServerWebExchange exchange, GatewayFilterChain chain ) {

//        exchange.getAttributes().put(GATEWAY_REQUEST_URL_ATTR, URI.create("http://localhost:8001/user/instances"));
//        exchange.getRequest().mutate().uri(URI.create("http://localhost:8001/user/instances")).build();
//        String token = exchange.getRequest().getQueryParams().getFirst("authToken");
//        //重定向(redirect)到登录页面
//        if (StringUtils.isBlank(token)) {
//            String url = "http://localhost:8001/user/instances";
//            ServerHttpResponse response = exchange.getResponse();
//            //303状态码表示由于请求对应的资源存在着另一个URI，应使用GET方法定向获取请求的资源
//            response.setStatusCode(HttpStatus.SEE_OTHER);
//            response.getHeaders().set(HttpHeaders.LOCATION, url);
//            return response.setComplete();
//        }
        return chain.filter(exchange);
    }


    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1001;
    }
}
